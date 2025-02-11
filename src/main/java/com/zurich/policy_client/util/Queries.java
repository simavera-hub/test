package com.zurich.policy_client.util;

public class Queries {
   
    public static final String GET_PROJECTS_BY_ID = "      SELECT new com.zurich.bts.timetracking.api.dto.UserProjectBeanDTO( u.id as userId, " +
    "             p.id as projectId, " +
    "             u.areaId as areaId, " +
    "             t.id as teamId, " +
    "             p.managerId as managerId, " +
    "             u2.name as managerName, " +
    "             t.active as activeTeam, " +           
    "             u.name as userName, " + 
    "             p.name as projectName, " + 
    "             pg.name as typeOfService, " + 
    "             pv.name as providerName, " +
    "             u.type as userRole ) " +
     "       FROM Team t " + 
     " INNER JOIN Project p ON t.projectId = p.id " +
     " INNER JOIN Program pg ON p.programId = pg.id " +
     " INNER JOIN User u ON t.userId = u.id " +
     " INNER JOIN User u2 ON p.managerId = u2.id " +
     " INNER JOIN Provider pv on pv.id = u.providerId " +
     "      WHERE t.userId = :userId " +
     "        AND (year(t.endDate)=YEAR(CURRENT_DATE) or t.endDate is null) " +
     "        AND t.active = 1";
    
     public static final String GET_WEEK_OF_THE_YEAR_BY_USERID = "  SELECT new com.zurich.bts.timetracking.api.dto.WeekOfTheYearDTO( pe.id as periodId, pr.id as projectId, pr.name as projectName, " + 
     "            r.hoursMonday, r.hoursTuesday, r.hoursWednesday, r.hoursThursday, " + 
     "            r.hoursFriday, r.hoursSaturday, r.hoursSunday, pr.available as available, CASE WHEN r.fullValidated='Y' THEN 1 ELSE 0 END )" + 
     "       FROM Register r " + 
     " INNER JOIN Period pe ON r.periodId = pe.id " + 
     " INNER JOIN User u ON pe.userId = u.id AND r.userId = u.id " + 
     " INNER JOIN Project pr ON r.projectId = pr.id " + 
     "      WHERE u.id = :userId " + 
     "        AND pe.id = :periodId  ";

     public static final String GET_TOTAL_WEEK_HOURS = "     SELECT new com.zurich.bts.timetracking.api.dto.TotalHoursWeekOfTheYearDTO( pe.id as periodId, 0 as projectId, '' as projectName,  " + 
     "            SUM(r.hoursMonday) as hoursMonday, SUM(r.hoursTuesday) as hoursTuesday, SUM(r.hoursWednesday) as hoursWednesday, SUM(r.hoursThursday) as hoursThursday,  " + 
     "            SUM(r.hoursFriday) as hoursFriday, SUM(r.hoursSaturday) as hoursSaturday, SUM(r.hoursSunday) as hoursSunday, 0 as available ) " + 
     "       FROM Register r " + 
     " INNER JOIN Period pe ON r.periodId = pe.id " + 
     " INNER JOIN User u ON pe.userId = u.id AND r.userId = u.id " + 
     "      WHERE u.id = :userId  " + 
     "        AND pe.id = :periodId  " +
     "   GROUP BY pe.id, pe.id ";

}