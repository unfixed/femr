package femr.business.services;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Query;
import com.google.inject.Inject;
import com.google.inject.Provider;
import femr.business.dtos.ServiceResponse;
import femr.common.models.*;
import femr.common.models.custom.ICustomField;
import femr.common.models.custom.ICustomTab;
import femr.common.models.custom.IPatientEncounterCustomField;
import femr.data.daos.IRepository;
import femr.data.models.*;
import femr.data.models.custom.CustomField;
import femr.data.models.custom.CustomTab;
import femr.data.models.custom.PatientEncounterCustomField;
import femr.ui.models.data.custom.CustomFieldItem;
import femr.ui.models.data.custom.CustomTabItem;
import femr.util.calculations.dateUtils;
import femr.util.stringhelpers.StringUtils;
import org.joda.time.DateTime;

import java.util.*;

public class MedicalService implements IMedicalService {

    private IRepository<IPatientEncounterTreatmentField> patientEncounterTreatmentFieldRepository;
    private IRepository<IPatientEncounterHpiField> patientEncounterHpiFieldRepository;
    private IRepository<IPatientEncounterPmhField> patientEncounterPmhFieldRepository;
    private IRepository<IPatientEncounterVital> patientEncounterVitalRepository;
    private IRepository<ICustomField> customFieldRepository;
    private IRepository<IPatientEncounterCustomField> patientEncounterCustomFieldRepository;
    private IRepository<IPatientPrescription> patientPrescriptionRepository;
    private IRepository<IHpiField> hpiFieldRepository;
    private IRepository<IPmhField> pmhFieldRepository;
    private IRepository<ITreatmentField> treatmentFieldRepository;
    private IRepository<IVital> vitalRepository;
    private IRepository<ICustomTab> customTabRepository;
    private Provider<IPatientEncounterVital> patientEncounterVitalProvider;


    @Inject
    public MedicalService(IRepository<IPatientEncounterTreatmentField> patientEncounterTreatmentFieldRepository,
                          IRepository<ICustomField> customFieldRepository,
                          IRepository<IPatientEncounterHpiField> patientEncounterHpiFieldRepository,
                          IRepository<IPatientEncounterPmhField> patientEncounterPmhFieldRepository,
                          IRepository<IPatientPrescription> patientPrescriptionRepository,
                          IRepository<IHpiField> hpiFieldRepository,
                          IRepository<IPmhField> pmhFieldRepository,
                          IRepository<ITreatmentField> treatmentFieldRepository,
                          IRepository<IPatientEncounterVital> patientEncounterVitalRepository,
                          IRepository<IVital> vitalRepository,
                          Provider<IPatientEncounterVital> patientEncounterVitalProvider,
                          IRepository<ICustomTab> customTabRepository,
                          IRepository<IPatientEncounterCustomField> patientEncounterCustomFieldRepository) {
        this.patientEncounterTreatmentFieldRepository = patientEncounterTreatmentFieldRepository;
        this.patientEncounterHpiFieldRepository = patientEncounterHpiFieldRepository;
        this.patientEncounterPmhFieldRepository = patientEncounterPmhFieldRepository;
        this.patientPrescriptionRepository = patientPrescriptionRepository;
        this.hpiFieldRepository = hpiFieldRepository;
        this.pmhFieldRepository = pmhFieldRepository;
        this.treatmentFieldRepository = treatmentFieldRepository;
        this.patientEncounterVitalRepository = patientEncounterVitalRepository;
        this.customFieldRepository = customFieldRepository;
        this.vitalRepository = vitalRepository;
        this.customTabRepository = customTabRepository;
        this.patientEncounterVitalProvider = patientEncounterVitalProvider;
        this.patientEncounterCustomFieldRepository = patientEncounterCustomFieldRepository;
    }

    @Override
    public ServiceResponse<List<? extends IPatientEncounterTreatmentField>> createPatientEncounterTreatmentFields(List<? extends IPatientEncounterTreatmentField> patientEncounterTreatmentFields) {
        List<? extends IPatientEncounterTreatmentField> newPatientEncounterTreatmentFields = patientEncounterTreatmentFieldRepository.createAll(patientEncounterTreatmentFields);
        ServiceResponse<List<? extends IPatientEncounterTreatmentField>> response = new ServiceResponse<>();

        if (newPatientEncounterTreatmentFields != null) {
            response.setResponseObject(newPatientEncounterTreatmentFields);
        } else {
            response.addError("patientEncounterTreatmentField", "Failed to save");
        }
        return response;
    }

    @Override
    public ServiceResponse<List<? extends IPatientPrescription>> createPatientPrescriptions(List<? extends IPatientPrescription> patientPrescriptions) {
        List<? extends IPatientPrescription> newPatientPrescriptions = patientPrescriptionRepository.createAll(patientPrescriptions);
        ServiceResponse<List<? extends IPatientPrescription>> response = new ServiceResponse<>();

        if (newPatientPrescriptions != null) {
            response.setResponseObject(newPatientPrescriptions);
        } else {
            response.addError("patientPrescription", "failed to save");
        }
        return response;
    }

    @Override
    public ServiceResponse<IPatientPrescription> createPatientPrescription(IPatientPrescription patientPrescription) {
        IPatientPrescription newPatientPrescription = patientPrescriptionRepository.create(patientPrescription);
        ServiceResponse<IPatientPrescription> response = new ServiceResponse<>();

        if (newPatientPrescription != null) {
            response.setResponseObject(newPatientPrescription);
        } else {
            response.addError("patientPrescription", "failed to save");
        }
        return response;
    }

    @Override
    public ServiceResponse<List<? extends IPatientEncounterHpiField>> createPatientEncounterHpiFields(List<? extends IPatientEncounterHpiField> patientEncounterHpiFields) {
        List<? extends IPatientEncounterHpiField> newPatientEncounterHpiFields = patientEncounterHpiFieldRepository.createAll(patientEncounterHpiFields);
        ServiceResponse<List<? extends IPatientEncounterHpiField>> response = new ServiceResponse<>();

        if (newPatientEncounterHpiFields != null) {
            response.setResponseObject(newPatientEncounterHpiFields);
        } else {
            response.addError("patientEncounterHpiField", "Failed to save");
        }
        return response;
    }

    @Override
    public ServiceResponse<List<? extends IPatientEncounterPmhField>> createPatientEncounterPmhFields(List<? extends IPatientEncounterPmhField> patientEncounterPmhFields) {
        List<? extends IPatientEncounterPmhField> newPatientEncounterPmhFields = patientEncounterPmhFieldRepository.createAll(patientEncounterPmhFields);
        ServiceResponse<List<? extends IPatientEncounterPmhField>> response = new ServiceResponse<>();

        if (newPatientEncounterPmhFields != null) {
            response.setResponseObject(newPatientEncounterPmhFields);
        } else {
            response.addError("patientEncounterPmhField", "Failed to save");
        }
        return response;
    }

    /*
    Determines if a patient has been seen by a doctor by checking for filled out
    fields in Hpi, Treatment, Prescription, or Pmh.
     */
    @Override
    public boolean hasPatientBeenCheckedInByPhysician(int encounterId) {
        ExpressionList<PatientEncounterHpiField> query = getPatientEncounterHpiField().where().eq("patient_encounter_id", encounterId);
        List<? extends IPatientEncounterHpiField> patientEncounterHpiFields = patientEncounterHpiFieldRepository.find(query);

        ExpressionList<PatientEncounterTreatmentField> query2 = getPatientEncounterTreatmentField().where().eq("patient_encounter_id", encounterId);
        List<? extends IPatientEncounterTreatmentField> patientEncounterTreatmentFields = patientEncounterTreatmentFieldRepository.find(query2);

        ExpressionList<PatientPrescription> query3 = getPatientPrescription().where().eq("encounter_id", encounterId);
        List<? extends IPatientPrescription> patientPrescriptions = patientPrescriptionRepository.find(query3);

        ExpressionList<PatientEncounterPmhField> query4 = getPatientEncounterPmhField().where().eq("patient_encounter_id", encounterId);
        List<? extends IPatientEncounterPmhField> patientEncounterPmhFields = patientEncounterPmhFieldRepository.find(query4);

        if (patientEncounterHpiFields.size() > 0) {
            return true;
        }
        if (patientEncounterPmhFields.size() > 0) {
            return true;
        }
        if (patientEncounterTreatmentFields.size() > 0) {
            return true;
        }
        if (patientPrescriptions.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public ServiceResponse<DateTime> getDateOfCheckIn(int encounterId) {
        ExpressionList<PatientEncounterHpiField> query = getPatientEncounterHpiField().where().eq("patient_encounter_id", encounterId);
        List<? extends IPatientEncounterHpiField> patientEncounterHpiFields = patientEncounterHpiFieldRepository.find(query);

        ExpressionList<PatientEncounterTreatmentField> query2 = getPatientEncounterTreatmentField().where().eq("patient_encounter_id", encounterId);
        List<? extends IPatientEncounterTreatmentField> patientEncounterTreatmentFields = patientEncounterTreatmentFieldRepository.find(query2);

        ExpressionList<PatientPrescription> query3 = getPatientPrescription().where().eq("encounter_id", encounterId);
        List<? extends IPatientPrescription> patientPrescriptions = patientPrescriptionRepository.find(query3);

        ExpressionList<PatientEncounterPmhField> query4 = getPatientEncounterPmhField().where().eq("patient_encounter_id", encounterId);
        List<? extends IPatientEncounterPmhField> patientEncounterPmhFields = patientEncounterPmhFieldRepository.find(query4);

        ServiceResponse<DateTime> response = new ServiceResponse<>();

        if (patientEncounterHpiFields.size() > 0) {
            response.setResponseObject(patientEncounterHpiFields.get(0).getDateTaken());
        } else if (patientEncounterPmhFields.size() > 0) {
            response.setResponseObject(patientEncounterPmhFields.get(0).getDateTaken());
        } else if (patientEncounterTreatmentFields.size() > 0) {
            response.setResponseObject(patientEncounterTreatmentFields.get(0).getDateTaken());
        } else if (patientPrescriptions.size() > 0) {
            response.setResponseObject(patientPrescriptions.get(0).getDateTaken());
        } else {
            response.addError("values", "That patient has not yet been seen");
        }
        return response;
    }

    @Override
    public ServiceResponse<Map<String, List<? extends IPatientEncounterPmhField>>> findPmhFieldsByEncounterId(int encounterId) {
        ServiceResponse<Map<String, List<? extends IPatientEncounterPmhField>>> response = new ServiceResponse<>();
        Map<String, List<? extends IPatientEncounterPmhField>> pmhValueMap = new LinkedHashMap<>();
        Query<PatientEncounterPmhField> query;

        List<? extends IPmhField> pmhFields = pmhFieldRepository.findAll(PmhField.class);
        List<? extends IPatientEncounterPmhField> patientPmh;
        String pmhFieldName;
        for (int pmhFieldIndex = 0; pmhFieldIndex < pmhFields.size(); pmhFieldIndex++) {
            pmhFieldName = pmhFields.get(pmhFieldIndex).getName().trim();
            query = getPatientEncounterPmhFieldQuery().fetch("pmhField").where().eq("patient_encounter_id", encounterId).eq("pmhField.name", pmhFieldName).order().desc("date_taken");
            patientPmh = patientEncounterPmhFieldRepository.find(query);
            pmhValueMap.put(pmhFieldName, patientPmh);
        }
        response.setResponseObject(pmhValueMap);
        return response;
    }

    @Override
    public ServiceResponse<Map<String, List<? extends IPatientEncounterTreatmentField>>> findTreatmentFieldsByEncounterId(int encounterId) {
        ServiceResponse<Map<String, List<? extends IPatientEncounterTreatmentField>>> response = new ServiceResponse<>();
        Map<String, List<? extends IPatientEncounterTreatmentField>> treatmentFieldMap = new LinkedHashMap<>();
        Query<PatientEncounterTreatmentField> query;

        List<? extends ITreatmentField> treatmentFields = treatmentFieldRepository.findAll(TreatmentField.class);
        List<? extends IPatientEncounterTreatmentField> patientTreatment;
        String treatmentFieldName;
        //loop through each available treatment field and build the map
        for (int treatmentFieldIndex = 0; treatmentFieldIndex < treatmentFields.size(); treatmentFieldIndex++) {
            treatmentFieldName = treatmentFields.get(treatmentFieldIndex).getName().trim();

            query = getPatientEncounterTreatmentFieldQuery().fetch("treatmentField").where().eq("patient_encounter_id", encounterId).eq("treatmentField.name", treatmentFieldName).order().desc("date_taken");
            patientTreatment = patientEncounterTreatmentFieldRepository.find(query);
            treatmentFieldMap.put(treatmentFieldName, patientTreatment);
        }
        response.setResponseObject(treatmentFieldMap);
        return response;
    }

    @Override
    public ServiceResponse<Map<String, List<? extends IPatientEncounterHpiField>>> findHpiFieldsByEncounterId(int encounterId) {
        ServiceResponse<Map<String, List<? extends IPatientEncounterHpiField>>> response = new ServiceResponse<>();
        Query<PatientEncounterHpiField> query;
        Map<String, List<? extends IPatientEncounterHpiField>> hpiValueMap = new LinkedHashMap<>();

        List<? extends IHpiField> hpiFields = hpiFieldRepository.findAll(HpiField.class);
        List<? extends IPatientEncounterHpiField> patientHpi;
        String hpiFieldName;
        for (int hpiFieldIndex = 0; hpiFieldIndex < hpiFields.size(); hpiFieldIndex++) {
            hpiFieldName = hpiFields.get(hpiFieldIndex).getName().trim();
            query = getPatientEncounterHpiFieldQuery().fetch("hpiField").where().eq("patient_encounter_id", encounterId).eq("hpiField.name", hpiFieldName).order().desc("date_taken");
            patientHpi = patientEncounterHpiFieldRepository.find(query);
            hpiValueMap.put(hpiFieldName, patientHpi);
        }
        response.setResponseObject(hpiValueMap);
        return response;
    }

    @Override
    public ServiceResponse<List<? extends IPatientEncounterVital>> createPatientEncounterVitals(Map<String, Float> patientEncounterVitalMap, int userId, int encounterId) {
        List<IPatientEncounterVital> patientEncounterVitals = new ArrayList<>();
        IPatientEncounterVital patientEncounterVital;
        IVital vital;

        ExpressionList<Vital> query;
        String currentTime = dateUtils.getCurrentDateTimeString();

        for (String key : patientEncounterVitalMap.keySet()) {
            if (patientEncounterVitalMap.get(key) != null) {
                query = getVitalQuery().where().eq("name", key);
                vital = vitalRepository.findOne(query);

                patientEncounterVital = patientEncounterVitalProvider.get();
                patientEncounterVital.setPatientEncounterId(encounterId);
                patientEncounterVital.setUserId(userId);
                patientEncounterVital.setDateTaken(currentTime);
                patientEncounterVital.setVital(vital);
                patientEncounterVital.setVitalValue(patientEncounterVitalMap.get(key));
                patientEncounterVitals.add(patientEncounterVital);
            }
        }

        List<? extends IPatientEncounterVital> newPatientEncounterVitals = patientEncounterVitalRepository.createAll(patientEncounterVitals);

        ServiceResponse<List<? extends IPatientEncounterVital>> response = new ServiceResponse<>();

        if (newPatientEncounterVitals != null) {
            response.setResponseObject(newPatientEncounterVitals);
        } else {
            response.addError("", "patient encounter vitals could not be saved to database");
        }
        return response;
    }

    @Override
    public ServiceResponse<Map<String, List<CustomFieldItem>>> getCustomFields(int encounterId) {
        ServiceResponse<Map<String, List<CustomFieldItem>>> response = new ServiceResponse<>();
        Map<String, List<CustomFieldItem>> customFieldMap = new HashMap<>();
        ExpressionList<CustomTab> query = getCustomMedicalTabQuery()
                .where()
                .eq("isDeleted", false);
        try {
            //O(n^2) because who gives a fuck
            List<? extends ICustomTab> customTabs = customTabRepository.find(query);
            for (ICustomTab ct : customTabs) {
                Query<CustomField> query2 = getCustomFieldQuery()
                        .fetch("customTab")
                        .where()
                        .eq("isDeleted", false)
                        .eq("customTab.name", ct.getName())
                        .order()
                        .asc("sort_order");
                List<? extends ICustomField> customFields = customFieldRepository.find(query2);
                List<CustomFieldItem> customFieldItems = new ArrayList<>();
                for (ICustomField cf : customFields) {
                    Query<PatientEncounterCustomField> query3 = getPatientEncounterCustomFieldQuery()
                            .where()
                            .eq("custom_field_id", cf.getId())
                            .eq("patient_encounter_id", encounterId)
                            .order()
                            .desc("date_taken");

                        List<? extends IPatientEncounterCustomField> patientEncounterCustomField = patientEncounterCustomFieldRepository.find(query3);
                    if (patientEncounterCustomField != null && patientEncounterCustomField.size() > 0){
                        customFieldItems.add(getCustomFieldItem(cf, patientEncounterCustomField.get(0).getCustomFieldValue()));
                    }else{
                        customFieldItems.add(getCustomFieldItem(cf, null));
                    }


                }
                customFieldMap.put(ct.getName(), customFieldItems);

            }
            response.setResponseObject(customFieldMap);
        } catch (Exception ex) {
            response.addError("", "error");
            return response;
        }

        return response;


    }

    @Override
    public ServiceResponse<List<CustomTabItem>> getCustomTabs() {
        ServiceResponse<List<CustomTabItem>> response = new ServiceResponse<>();
        ExpressionList<CustomTab> query = getCustomMedicalTabQuery()
                .where()
                .eq("isDeleted", false);
        try {
            List<? extends ICustomTab> customTabs = customTabRepository.find(query);
            List<CustomTabItem> customTabNames = new ArrayList<>();

            for (ICustomTab ct : customTabs) {
                customTabNames.add(getCustomTabItem(ct));
            }
            response.setResponseObject(customTabNames);
        } catch (Exception ex) {
            response.addError("", "error");
        }

        return response;
    }

    @Override
    public ServiceResponse<List<CustomFieldItem>> createPatientEncounterCustomFields(List<CustomFieldItem> customFieldItems, int encounterId, int userId) {
        ServiceResponse<List<CustomFieldItem>> response = new ServiceResponse<>();
        List<IPatientEncounterCustomField> customFields = new ArrayList<>();
        try {


            for (CustomFieldItem cf : customFieldItems) {
                ExpressionList<CustomField> query = getCustomFieldQuery()
                        .where()
                        .eq("name", cf.getName());
                ICustomField customField = customFieldRepository.findOne(query);
                IPatientEncounterCustomField patientEncounterCustomField = new PatientEncounterCustomField();
                patientEncounterCustomField.setUserId(userId);
                patientEncounterCustomField.setPatientEncounterId(encounterId);
                patientEncounterCustomField.setDateTaken(DateTime.now());
                patientEncounterCustomField.setCustomFieldValue(cf.getValue());
                patientEncounterCustomField.setCustomFieldId(customField.getId());
                ExpressionList<PatientEncounterCustomField> query2 = getPatientEncounterCustomFieldQuery()
                        .where()
                        .eq("custom_field_id", customField.getId())
                        .eq("patient_encounter_id", encounterId)
                        .eq("custom_field_value", cf.getValue());
                if (patientEncounterCustomFieldRepository.findOne(query2) != null){
                    //already exists
                }else{
                    customFields.add(patientEncounterCustomField);
                }

            }
            patientEncounterCustomFieldRepository.createAll(customFields);
            response.setResponseObject(customFieldItems);
        } catch (Exception ex) {
            response.addError("", "error");
        }
        return response;

    }

    private CustomTabItem getCustomTabItem(ICustomTab ct) {
        CustomTabItem customTabItem = new CustomTabItem();
        customTabItem.setLeftColumnSize(ct.getLeftColumnSize());
        customTabItem.setRightColumnSize(ct.getRightColumnSize());
        customTabItem.setName(ct.getName());
        return customTabItem;
    }

    private CustomFieldItem getCustomFieldItem(ICustomField cf, String value) {
        CustomFieldItem customFieldItem = new CustomFieldItem();
        customFieldItem.setName(cf.getName());
        customFieldItem.setType(cf.getCustomFieldType().getName());
        customFieldItem.setSize(cf.getCustomFieldSize().getName());
        customFieldItem.setOrder(cf.getOrder());
        customFieldItem.setPlaceholder(cf.getPlaceholder());
        if (StringUtils.isNotNullOrWhiteSpace(value)){
            customFieldItem.setValue(value);
        }
        return customFieldItem;
    }

    private Query<PatientEncounterCustomField> getPatientEncounterCustomFieldQuery(){
        return Ebean.find(PatientEncounterCustomField.class);
    }

    private Query<CustomTab> getCustomMedicalTabQuery() {
        return Ebean.find(CustomTab.class);
    }

    private Query<CustomField> getCustomFieldQuery() {
        return Ebean.find(CustomField.class);
    }

    private Query<PatientEncounterHpiField> getPatientEncounterHpiField() {
        return Ebean.find(PatientEncounterHpiField.class);
    }

    private Query<PatientEncounterPmhField> getPatientEncounterPmhField() {
        return Ebean.find(PatientEncounterPmhField.class);
    }

    private Query<PatientEncounterTreatmentField> getPatientEncounterTreatmentField() {
        return Ebean.find(PatientEncounterTreatmentField.class);
    }

    private Query<PatientPrescription> getPatientPrescription() {
        return Ebean.find(PatientPrescription.class);
    }

    private Query<PatientEncounterTreatmentField> getPatientEncounterTreatmentFieldQuery() {
        return Ebean.find(PatientEncounterTreatmentField.class);
    }

    private Query<PatientEncounterHpiField> getPatientEncounterHpiFieldQuery() {
        return Ebean.find(PatientEncounterHpiField.class);
    }

    private Query<PatientEncounterPmhField> getPatientEncounterPmhFieldQuery() {
        return Ebean.find(PatientEncounterPmhField.class);
    }

    private Query<Vital> getVitalQuery() {
        return Ebean.find(Vital.class);
    }
}
