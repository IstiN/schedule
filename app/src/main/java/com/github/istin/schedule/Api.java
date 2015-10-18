package com.github.istin.schedule;

/**
 * Created by uladzimir_klyshevich on 10/13/15.
 */
public class Api {

    public static final String BASE_URL = "http://api.grsu.by/1.x/app1/";

    public static final String LECTURER_LIST = BASE_URL + "getTeachers";

    public static final String FACULTY_LIST = BASE_URL + "getFaculties";

    public static final String DEPARTMENT_LIST = BASE_URL + "getDepartments";

    public static final String GROUP_LIST = BASE_URL + "getGroups" + "?departmentId=2&facultyId=3&course=4";

    public static final String LECTURER_SCHEDULE = BASE_URL + "getTeacherSchedule"; // + "?teacherId=132435&dateStart=18.10.2015&dateEnd=18.11.2015";

    public static final String GROUP_SCHEDULE = BASE_URL + "getGroupSchedule"; // + "?groupId=1407&dateStart=18.10.2015&dateEnd=18.11.2015";

    public static final String UNIVERSITY_LIST = "http://schedule-1097.appspot.com/" + "university_list";

}
