/**
 *Copyright (c) 2017 deepali
 *Project : devone(V1.0)
 */


package com.app.customexceptions;

import com.spartan.pluggable.exception.layers.ds.BusinessRuleUnableToProceedException;


/**
 *Author : deepali  arvind
 *Date :Mon Jan 09 08:01:04 UTC 2017
 */

public class OutOfRange extends BusinessRuleUnableToProceedException {


     private static final long serialVersionUID = 1501826722671665243L;


     /** Create OutOfRange with Alarm Id & Throwable.
      * @param _appAlarmId
      * @param _throwable
      */
     public OutOfRange(final String _appAlarmId, Throwable _throwable) {
          super("OutOfRange", _appAlarmId, _throwable);
     }

     /** Create OutOfRange with Message, Alarm Id & Throwable.
      * @param _msg
      * @param _appAlarmId
      * @param _throwable
      */
     public OutOfRange(final String _msg, final String _appAlarmId, final Throwable _throwable) {
          super(_msg, _appAlarmId, _throwable);
     }

     /** Create OutOfRange with Message, Alarm Id, Throwable & Variable Arguments.
      * @param _msg
      * @param _appAlarmId
      * @param _throwable
      * @param _params
      */
     public OutOfRange(final String _msg, final String _appAlarmId, final Throwable _throwable, final Object..._params) {
          super(_msg, _appAlarmId, _throwable, _params);
     }

}