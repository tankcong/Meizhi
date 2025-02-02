/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.drakeet.meizhi.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.Date;

import me.drakeet.meizhi.service.AlarmReceiver;

/**
 * Created by drakeet on 7/1/15.
 */
public class AlarmManagerUtils {

    public static void register(Context context) {

        Calendar today = Calendar.getInstance();
        today.setTime(new Date()); // TODO: 15/10/9 remove

        today.set(Calendar.HOUR_OF_DAY, 12);
        today.set(Calendar.MINUTE, 38);
        today.set(Calendar.SECOND, 0);

        Intent intent = new Intent("me.drakeet.meizhi.alarm");
        intent.setClass(context, AlarmReceiver.class);

        PendingIntent pi = PendingIntent.getBroadcast(context, 520, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        am.set(AlarmManager.RTC_WAKEUP, today.getTimeInMillis(), pi);
        am.set(AlarmManager.RTC_WAKEUP, today.getTimeInMillis(), pi); // TODO: 15/10/9 test set
        // two alarm
    }
}
