/*
     fEMR - fast Electronic Medical Records
     Copyright (C) 2014  Team fEMR

     fEMR is free software: you can redistribute it and/or modify
     it under the terms of the GNU General Public License as published by
     the Free Software Foundation, either version 3 of the License, or
     (at your option) any later version.

     fEMR is distributed in the hope that it will be useful,
     but WITHOUT ANY WARRANTY; without even the implied warranty of
     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
     GNU General Public License for more details.

     You should have received a copy of the GNU General Public License
     along with fEMR.  If not, see <http://www.gnu.org/licenses/>. If
     you have any questions, contact <info@teamfemr.org>.
*/
package femr.business.services;


import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Query;
import com.google.inject.Inject;
import com.google.inject.Provider;
import femr.business.helpers.DomainMapper;
import femr.business.helpers.QueryHelper;
import femr.business.helpers.QueryProvider;
import femr.common.dto.ServiceResponse;
import femr.data.daos.IRepository;
import femr.data.models.*;
import femr.common.models.PatientEncounterItem;
import femr.common.models.PatientItem;
import femr.common.models.VitalItem;
import femr.common.models.ResearchItem;
import femr.util.calculations.dateUtils;
import femr.util.stringhelpers.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

public class ResearchService implements IResearchService {


    //repositories
    private final IRepository<IChiefComplaint> chiefComplaintRepository;
    private final IRepository<IPatient> patientRepository;
    private final IRepository<IPatientEncounter> patientEncounterRepository;
    private final IRepository<IPatientEncounterVital> patientEncounterVitalRepository;
    private final IRepository<IUser> userRepository;
    private final IRepository<IVital> vitalRepository;
    private final Provider<IPatientEncounterVital> patientEncounterVitalProvider;

    private final DomainMapper domainMapper;

    /**
     * Initializes the research service and injects the dependence
     */
    @Inject
    public ResearchService(IRepository<IChiefComplaint> chiefComplaintRepository,
                           IRepository<IPatient> patientRepository,
                           IRepository<IPatientEncounter> patientEncounterRepository,
                           IRepository<IPatientEncounterVital> patientEncounterVitaRepository,
                           IRepository<IUser> userRepository,
                           IRepository<IVital> vitalRepository,
                           Provider<IPatientEncounterVital> patientEncounterVitalProvider,

                           DomainMapper domainMapper) {
        this.chiefComplaintRepository = chiefComplaintRepository;
        this.patientRepository = patientRepository;
        this.patientEncounterRepository = patientEncounterRepository;
        this.patientEncounterVitalRepository = patientEncounterVitaRepository;
        this.userRepository = userRepository;
        this.vitalRepository = vitalRepository;
        this.patientEncounterVitalProvider = patientEncounterVitalProvider;
        this.domainMapper = domainMapper;
    }

    //TODO: Implement research business logic

    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceResponse<List<PatientItem>> getAllPatientItems() {
        ServiceResponse<List<PatientItem>> response = new ServiceResponse<>();

        try {
            List<? extends IPatient> patients = patientRepository.findAll(Patient.class);
            List<PatientItem> patientItems = new ArrayList<>();
            for (IPatient v : patients) {
                float temp = 2;
                patientItems.add(domainMapper.createPatientItem(v, 2, 2, 2, temp));
            }
            response.setResponseObject(patientItems);
        } catch (Exception ex) {
            response.addError("exception", ex.getMessage());
        }
        /*
        for (PatientItem v : response.getResponseObject())
        {
            System.out.println(v.getId());
            System.out.println(v.getBirth());
        }
        */
        return response;
    }

    public ServiceResponse<List<Date>> getAllPatientAges() {
        ServiceResponse<List<Date>> response = new ServiceResponse<>();

        try {
            List<? extends IPatient> patients = patientRepository.findAll(Patient.class);
            List<Date> patientAges = new ArrayList<>();
            for (IPatient patientAge : patients) {
                patientAges.add(patientAge.getAge());
            }
            response.setResponseObject(patientAges);
        } catch (Exception ex) {
            response.addError("exception", ex.getMessage());
        }




        return response;
    }

    /**
     * {@inheritDoc}
     */
    public ServiceResponse<Map<Integer, VitalItem>> getPatientVitals(String vitalName, String startDateString, String endDateString) {

        ServiceResponse<Map<Integer, VitalItem>> response = new ServiceResponse<>();

        try {

            ExpressionList<Vital> vitalQuery = QueryProvider.getVitalQuery().where().eq("name", vitalName);
            IVital v = vitalRepository.findOne(vitalQuery);

            SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            // Set Start Date to start of day
            String startParseDate = startDateString + " 00:00:00";
            Date startDateObj = sqlFormat.parse(startParseDate);
            // Set End Date to end of day
            String parseEndDate = endDateString + " 23:59:59";
            Date endDateObj = sqlFormat.parse(parseEndDate);

            Query q = QueryProvider.getPatientEncounterVitalQuery();
            q.where()
                    .gt("dateTaken", sqlFormat.format(startDateObj))
                    .lt("dateTaken", sqlFormat.format(endDateObj))
                    .eq("vital", v)
                    .orderBy("dateTaken")
                    .findList();

            // Map indexed by patientEncounterId ensures only the last reading of the encounter will be counted
            Map<Integer, VitalItem> vitals = new HashMap<>();
            List<? extends IPatientEncounterVital> patientEncounters = patientEncounterVitalRepository.find(q);
            for (IPatientEncounterVital eVital : patientEncounters) {

                VitalItem vital = new VitalItem();
                vital.setName(vitalName);
                vital.setValue(eVital.getVitalValue());

                vitals.put(eVital.getPatientEncounterId(), vital);
            }
            response.setResponseObject(vitals);

        } catch (Exception ex) {

            response.addError("exception", ex.getMessage());
        }




        return response;
    }


    public ServiceResponse<List<ResearchItem>> getPatientAttribute(String attributeName, String startDateString, String endDateString) {

        ServiceResponse<List<ResearchItem>> response = new ServiceResponse<>();

        try {

            SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            // Set Start Date to start of day
            String startParseDate = startDateString + " 00:00:00";
            Date startDateObj = sqlFormat.parse(startParseDate);
            // Set End Date to end of day
            String parseEndDate = endDateString + " 23:59:59";
            Date endDateObj = sqlFormat.parse(parseEndDate);

            Query q = QueryProvider.getPatientQuery();
            q.where()
                    //.gt("dateTaken", sqlFormat.format(startDateObj))
                    //.lt("dateTaken", sqlFormat.format(endDateObj))
                    //.eq("vital", v)
                    .orderBy("id")
                    .findList();


            List<? extends IPatient> p = patientRepository.find(q);
            List<ResearchItem> researchItems = new ArrayList<>();

            for (IPatient patient : p) {

                switch (attributeName) {
                    case "age":
                        researchItems.add(
                                new ResearchItem(
                                        patient.getId(),
                                        "age",
                                        dateUtils.getAgeFloat(patient.getAge())
                                )
                        );
                        break;
                    case "gender":
                        int gender = 0;
                        if (patient.getSex() == "male") {gender = 1;}
                        else if (patient.getSex() == "female") {gender = 2;}
                        researchItems.add(
                                new ResearchItem(
                                        patient.getId(),
                                        "gender",
                                        (float) gender
                                )
                        );
                        break;
                }
            }

        } catch (Exception ex) {
            response.addError("exception", ex.getMessage());
        }

        return response;

    }

    /*




    ReturnData
    - patient ID
    -

    Demographics
    ** data from patients table
    ** date from patient_encouters
    - Age
    - Gender

    ** data and date from patient_encounters table
    - Pregnancy Status (Weeks > 0 || weeks != null => yes)
    - Weeks Pregnant

    Vitals
    ** id from vitals table
    ** data from patient_encounter_vitals table
    ** date from patient_encounters table
    - Temperature
    - Blood Pressure (Systolic, Diastolic)
    - Heart Rate
    - Respirations
    - Pregnancy Status
    - Oxygen Saturation
    - Glucose
    - Height (feet, inches)
    - Weight

     */


}
