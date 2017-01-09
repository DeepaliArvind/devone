/**
 *Copyright (c) 2017 deepali
 *Project : devone(V1.0)
 */


package com.app.customexceptions;

import com.spartan.pluggable.exception.layers.ds.BusinessRuleNotFoundException;


/**
 *Author : deepali  arvind
 *Date :Mon Jan 09 08:01:04 UTC 2017
 */

public class TransactioFailed extends BusinessRuleNotFoundException {


     private static final long serialVersionUID = 1441893495377684090L;


     /** Create TransactioFailed with Alarm Id & Throwable.
      * @param _appAlarmId
      * @param _throwable
      */
     public TransactioFailed(final String _appAlarmId, Throwable _throwable) {
          super("TransactioFailed", _appAlarmId, _throwable);
     }

     /** Create TransactioFailed with Message, Alarm Id & Throwable.
      * @param _msg
      * @param _appAlarmId
      * @param _throwable
      */
     public TransactioFailed(final String _msg, final String _appAlarmId, final Throwable _throwable) {
          super(_msg, _appAlarmId, _throwable);
     }

     /** Create TransactioFailed with Message, Alarm Id, Throwable & Variable Arguments.
      * @param _msg
      * @param _appAlarmId
      * @param _throwable
      * @param _params
      */
     public TransactioFailed(final String _msg, final String _appAlarmId, final Throwable _throwable, final Object..._params) {
          super(_msg, _appAlarmId, _throwable, _params);
     }

}