/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this service wrapper you may modify the generated sub-class of this class - JobManager.as.
 */
package com.likya.pinara.services.jobmanager
{
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.services.wrapper.HTTPServiceWrapper;
import mx.rpc.AbstractOperation;
import mx.rpc.AsyncToken;
import mx.rpc.http.HTTPMultiService;
import mx.rpc.http.Operation;

import com.adobe.serializers.xml.XMLSerializationFilter;

[ExcludeClass]
internal class _Super_JobManager extends com.adobe.fiber.services.wrapper.HTTPServiceWrapper
{
    private static var serializer0:XMLSerializationFilter = new XMLSerializationFilter();

    // Constructor
    public function _Super_JobManager()
    {
        // initialize service control
        _serviceControl = new mx.rpc.http.HTTPMultiService("http://127.0.0.1:3000");
         var operations:Array = new Array();
         var operation:mx.rpc.http.Operation;
         var argsArray:Array;

         operation = new mx.rpc.http.Operation(null, "stop");
         operation.url = "/flex/restsrvc/stopjob/{jobId}";
         operation.method = "GET";
         argsArray = new Array("jobId");
         operation.argumentNames = argsArray;         
         operation.serializationFilter = serializer0;
         operation.properties = new Object();
         operation.properties["urlParamNames"] = ["jobId"];
         operation.resultType = Object;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "start");
         operation.url = "/flex/restsrvc/startjob/{jobId}";
         operation.method = "GET";
         argsArray = new Array("jobId");
         operation.argumentNames = argsArray;         
         operation.serializationFilter = serializer0;
         operation.properties = new Object();
         operation.properties["urlParamNames"] = ["jobId"];
         operation.resultType = Object;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "setsuccess");
         operation.url = "/flex/restsrvc/setsuccessjob/{jobId}";
         operation.method = "GET";
         argsArray = new Array("jobId");
         operation.argumentNames = argsArray;         
         operation.serializationFilter = serializer0;
         operation.properties = new Object();
         operation.properties["urlParamNames"] = ["jobId"];
         operation.resultType = Object;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "skip");
         operation.url = "/flex/restsrvc/skipjob/{jobId}";
         operation.method = "GET";
         argsArray = new Array("jobId");
         operation.argumentNames = argsArray;         
         operation.serializationFilter = serializer0;
         operation.properties = new Object();
         operation.properties["urlParamNames"] = ["jobId"];
         operation.resultType = Object;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "pause");
         operation.url = "/flex/restsrvc/pausejob/{jobId}";
         operation.method = "GET";
         argsArray = new Array("jobId");
         operation.argumentNames = argsArray;         
         operation.serializationFilter = serializer0;
         operation.properties = new Object();
         operation.properties["urlParamNames"] = ["jobId"];
         operation.resultType = Object;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "resume");
         operation.url = "/flex/restsrvc/resumejob/{jobId}";
         operation.method = "POST";
         argsArray = new Array("jobId");
         operation.argumentNames = argsArray;         
         operation.serializationFilter = serializer0;
         operation.properties = new Object();
         operation.properties["urlParamNames"] = ["jobId"];
         operation.contentType = "application/x-www-form-urlencoded";
         operation.resultType = Object;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "retry");
         operation.url = "/flex/restsrvc/retryjob/{jobId}";
         operation.method = "POST";
         argsArray = new Array("jobId");
         operation.argumentNames = argsArray;         
         operation.serializationFilter = serializer0;
         operation.properties = new Object();
         operation.properties["urlParamNames"] = ["jobId"];
         operation.contentType = "application/x-www-form-urlencoded";
         operation.resultType = Object;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "enable");
         operation.url = "/flex/restsrvc/enablejob/{jobId}";
         operation.method = "GET";
         argsArray = new Array("jobId");
         operation.argumentNames = argsArray;         
         operation.serializationFilter = serializer0;
         operation.properties = new Object();
         operation.properties["urlParamNames"] = ["jobId"];
         operation.resultType = Object;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "disable");
         operation.url = "/flex/restsrvc/disablejob/{jobId}";
         operation.method = "GET";
         operation.serializationFilter = serializer0;
         operation.properties = new Object();
         operation.properties["urlParamNames"] = ["jobId"];
         operation.contentType = "application/xml";
         operation.resultType = Object;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "stopapp");
         operation.url = "/flex/restsrvc/stopapp";
         operation.method = "GET";
         operation.serializationFilter = serializer0;
         operation.resultType = Object;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "fstopapp");
         operation.url = "/flex/restsrvc/fstopapp";
         operation.method = "GET";
         operation.serializationFilter = serializer0;
         operation.resultType = Object;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "suspendapp");
         operation.url = "/flex/restsrvc/suspendapp";
         operation.method = "GET";
         operation.serializationFilter = serializer0;
         operation.resultType = String;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "resumeapp");
         operation.url = "/flex/restsrvc/resumeapp";
         operation.method = "GET";
         operation.serializationFilter = serializer0;
         operation.resultType = String;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "addjob");
         operation.url = "/flex/restsrvc/addjob/";
         operation.method = "POST";
         operation.serializationFilter = serializer0;
         operation.contentType = "application/xml";
         operation.resultType = Object;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "updatejob");
         operation.url = "/flex/restsrvc/updatejob/";
         operation.method = "POST";
         operation.serializationFilter = serializer0;
         operation.contentType = "application/xml";
         operation.resultType = Object;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "deletejob");
         operation.url = "/flex/restsrvc/deletejob/";
         operation.method = "POST";
         operation.serializationFilter = serializer0;
         operation.contentType = "application/xml";
         operation.resultType = Object;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "recover");
         operation.url = "/flex/restsrvc/recover";
         operation.method = "GET";
         operation.resultType = Object;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "norecover");
         operation.url = "/flex/restsrvc/norecover";
         operation.method = "GET";
         operation.resultType = Object;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "getappstate");
         operation.url = "/flex/restsrvc/appstate";
         operation.method = "GET";
         operation.resultType = Object;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "enablegrp");
         operation.url = "/flex/restsrvc/enablegrp/{grpId}";
         operation.method = "GET";
         argsArray = new Array("grpId");
         operation.argumentNames = argsArray;         
         operation.properties = new Object();
         operation.properties["urlParamNames"] = ["grpId"];
         operation.resultType = Object;
         operations.push(operation);

         operation = new mx.rpc.http.Operation(null, "disablegrp");
         operation.url = "/flex/restsrvc/disablegrp/{grpId}";
         operation.method = "GET";
         argsArray = new Array("grpId");
         operation.argumentNames = argsArray;         
         operation.properties = new Object();
         operation.properties["urlParamNames"] = ["grpId"];
         operation.resultType = Object;
         operations.push(operation);

         _serviceControl.operationList = operations;  


         preInitializeService();
         model_internal::initialize();
    }
    
    //init initialization routine here, child class to override
    protected function preInitializeService():void
    {
      
    }
    

    /**
      * This method is a generated wrapper used to call the 'stop' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function stop(jobId:String) : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("stop");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(jobId) ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'start' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function start(jobId:String) : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("start");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(jobId) ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'setsuccess' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function setsuccess(jobId:String) : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("setsuccess");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(jobId) ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'skip' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function skip(jobId:String) : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("skip");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(jobId) ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'pause' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function pause(jobId:String) : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("pause");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(jobId) ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'resume' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function resume(jobId:String) : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("resume");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(jobId) ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'retry' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function retry(jobId:String) : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("retry");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(jobId) ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'enable' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function enable(jobId:String) : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("enable");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(jobId) ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'disable' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function disable(jobId:String) : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("disable");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(jobId) ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'stopapp' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function stopapp() : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("stopapp");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send() ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'fstopapp' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function fstopapp() : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("fstopapp");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send() ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'suspendapp' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function suspendapp() : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("suspendapp");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send() ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'resumeapp' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function resumeapp() : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("resumeapp");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send() ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'addjob' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function addjob(strXml:String) : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("addjob");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(strXml) ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'updatejob' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function updatejob(strXml:String) : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("updatejob");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(strXml) ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'deletejob' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function deletejob(strXml:String) : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("deletejob");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(strXml) ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'recover' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function recover() : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("recover");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send() ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'norecover' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function norecover() : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("norecover");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send() ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'getappstate' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function getappstate() : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("getappstate");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send() ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'enablegrp' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function enablegrp(grpId:String) : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("enablegrp");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(grpId) ;
        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'disablegrp' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function disablegrp(grpId:String) : mx.rpc.AsyncToken
    {
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("disablegrp");
		var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(grpId) ;
        return _internal_token;
    }
     
}

}
