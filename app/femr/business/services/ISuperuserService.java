package femr.business.services;


import femr.business.dtos.ServiceResponse;
import femr.business.dtos.TabFieldItem;
import femr.business.dtos.TabItem;

import java.util.List;

public interface ISuperuserService {

    /**
     * Edit a tab field
     *
     * @param customFieldItem new tab field item being created
     * @return ServiceResponse object containing the new TabFieldItem with possible exceptions
     */
    ServiceResponse<TabFieldItem> editTabField(TabFieldItem customFieldItem);

    /**
     * Edit a tab - updates the date created, left column size, right column size,
     * userId
     *
     * @param customTabItem new tab item being created
     * @param userId user editing the tab
     * @return ServiceResponse object containing the new TabItem with possible exceptions
     */
    ServiceResponse<TabItem> editTab(TabItem customTabItem, int userId);

    /**
     * Deletes or un-Deletes a tab
     *
     * @param name name of the tab (unique identifier)
     * @return ServiceResponse object containing TabItem with possible exceptions
     */
    ServiceResponse<TabItem> toggleTab(String name);

    /**
     * Deletes or un-Deletes a tab field
     *
     * @param fieldName name of the field to toggle
     * @param tabName name of the tab the fields is in
     * @return ServuceResponse object and TabFieldItem that was toggled with possible expceptions
     */
    ServiceResponse<TabFieldItem> toggleTabField(String fieldName, String tabName);

    /**
     * Create a new tab
     *
     * @param newTab the new TabItem being created
     * @param userId id of the user that is creating the tab
     * @return ServiceResponse containing the same TabItem with possible exceptions
     */
    ServiceResponse<TabItem> createTab(TabItem newTab, int userId);

    /**
     * Create a new tab field
     *
     * @param customFieldItem the new TabFieldItem being created
     * @param userId id of the user that is creating the tab field
     * @param tabName name of the tab the fields is in
     * @return a new TabFieldItem based on the saved TabField with possible exceptions
     */
    ServiceResponse<TabFieldItem> createTabField(TabFieldItem customFieldItem, int userId, String tabName);

    /**
     * Get all Tabs based on whether or not they are deleted
     *
     * @param isDeleted whether or not the tabs are deleted
     * @return a list of all TabItems with possible exceptions
     */
    ServiceResponse<List<TabItem>> getTabs(Boolean isDeleted);

    /**
     * Get all fields for one tab
     *
     * @param tabName name of the tab to get fields for
     * @param isDeleted whether or not the fields are deleted
     * @return a list of fields for the tab with possible exceptions
     */
    ServiceResponse<List<TabFieldItem>> getTabFields(String tabName, Boolean isDeleted);

    /**
     * Get all possible types of tab fields
     *
     * @return list of the names of the tab field types with possible exceptions
     */
    ServiceResponse<List<String>> getTypes();

    /**
     * Get all possible sizes of tab fields
     *
     * @return list of the names of the tab field sizes with possible exceptions
     */
    ServiceResponse<List<String>> getSizes();

    /**
     * Checks to see if a tab field exists
     *
     * @param fieldName name of the field (unique identifier)
     * @return Service response indicating whether or not it exists
     */
    ServiceResponse<Boolean> doesTabFieldExist(String fieldName);

    /**
     * Checks to see if a tab exists
     *
     * @param tabName name of the tab (unique identifier)
     * @return Service response indicating whether or not it exists
     */
    ServiceResponse<Boolean> doesTabExist(String tabName);

}
