/**
 *Copyright (c) 2017 deepali
 *Project : devone(V1.0)
 */


package com.app.customexceptions;

import com.spartan.pluggable.exception.layers.ds.NotificationFailedException;


/**
 *Author : deepali  arvind
 *Date :Mon Jan 09 08:01:04 UTC 2017
 */

public class InvalidCard extends NotificationFailedException {


     private static final long serialVersionUID = 7984487216559919344L;


     /** Create InvalidCard with Alarm Id & Throwable.
      * @param _appAlarmId
      * @param _throwable
      */
     public InvalidCard(final String _appAlarmId, Throwable _throwable) {
          super("InvalidCard", _appAlarmId, _throwable);
     }

     /** Create InvalidCard with Message, Alarm Id & Throwable.
      * @param _msg
      * @param _appAlarmId
      * @param _throwable
      */
     public InvalidCard(final String _msg, final String _appAlarmId, final Throwable _throwable) {
          super(_msg, _appAlarmId, _throwable);
     }

     /** Create InvalidCard with Message, Alarm Id, Throwable & Variable Arguments.
      * @param _msg
      * @param _appAlarmId
      * @param _throwable
      * @param _params
      */
     public InvalidCard(final String _msg, final String _appAlarmId, final Throwable _throwable, final Object..._params) {
          super(_msg, _appAlarmId, _throwable, _params);
     }

}