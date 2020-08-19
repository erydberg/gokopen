package se.gokopen.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import se.gokopen.model.ConfigRegistration;

public class RegistrationChecker {

    private RegistrationChecker() {
    }

    public static boolean isOpenForRegistration(ConfigRegistration config, int noOfRegisteredPatrolsNow) {
        int maxPatrols = 0;
        boolean allowRegistration = false;

        if (null != config.getMaxPatrols()) {
            maxPatrols = config.getMaxPatrols();
        }
        if (null != config.getAllowPublicRegistration()) {
            allowRegistration = config.getAllowPublicRegistration();
        }

        return allowRegistration && isOpenToday(config.getFirstRegisterDay(), config.getLastRegisterDay())
                && isItSeatsLeft(maxPatrols, noOfRegisteredPatrolsNow);
    }

    public static boolean isOpenToday(Date firstRegistrationDate, Date lastRegistrationDate) {
        if (firstRegistrationDate == null || lastRegistrationDate == null) {
            return false;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String firstRegister = sdf.format(firstRegistrationDate);
            String lastRegister = sdf.format(lastRegistrationDate);
            String today = sdf.format(new Date());
            Date firstDate = sdf.parse(firstRegister);
            Date lastDate = sdf.parse(lastRegister);
            Date todayDate = sdf.parse(today);

            if (!todayDate.after(lastDate) && !todayDate.before(firstDate)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isItSeatsLeft(int maxSeats, int registeredPatrolsNow) {
        if (maxSeats == 0) {
            return true;
        }
        if (maxSeats > registeredPatrolsNow) {
            return true;
        }
        return false;
    }
}
