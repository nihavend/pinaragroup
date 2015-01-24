/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this service wrapper you may modify the generated sub-class of this class - Query.as.
 */
package services.query
{
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.services.wrapper.WebServiceWrapper;
import com.adobe.serializers.utility.TypeUtility;
import flash.utils.ByteArray;
import mx.rpc.AbstractOperation;
import mx.rpc.AsyncToken;
import mx.rpc.soap.mxml.Operation;
import mx.rpc.soap.mxml.WebService;
import valueObjects.Collection;
import valueObjects.QueryResponse;

[ExcludeClass]
internal class _Super_Query extends com.adobe.fiber.services.wrapper.WebServiceWrapper
{
     
    // Constructor
    public function _Super_Query()
    {
        // initialize service control
        _serviceControl = new mx.rpc.soap.mxml.WebService();
        var operations:Object = new Object();
        var operation:mx.rpc.soap.mxml.Operation;

        operation = new mx.rpc.soap.mxml.Operation(null, "disconnect");
        operations["disconnect"] = operation;

        operation = new mx.rpc.soap.mxml.Operation(null, "listCollection");
         operation.resultType = valueObjects.Collection;
        operations["listCollection"] = operation;

        operation = new mx.rpc.soap.mxml.Operation(null, "getResource");
         operation.resultType = String;
        operations["getResource"] = operation;

        operation = new mx.rpc.soap.mxml.Operation(null, "getResourceData");
         operation.resultType = ByteArray;
        operations["getResourceData"] = operation;

        operation = new mx.rpc.soap.mxml.Operation(null, "connect");
         operation.resultType = String;
        operations["connect"] = operation;

        operation = new mx.rpc.soap.mxml.Operation(null, "retrieve");
         operation.resultElementType = String;
        operations["retrieve"] = operation;

        operation = new mx.rpc.soap.mxml.Operation(null, "retrieveByDocument");
         operation.resultElementType = String;
        operations["retrieveByDocument"] = operation;

        operation = new mx.rpc.soap.mxml.Operation(null, "xquery");
         operation.resultType = valueObjects.QueryResponse;
        operations["xquery"] = operation;

        operation = new mx.rpc.soap.mxml.Operation(null, "retrieveData");
         operation.resultElementType = ByteArray;
        operations["retrieveData"] = operation;

        operation = new mx.rpc.soap.mxml.Operation(null, "query");
         operation.resultType = valueObjects.QueryResponse;
        operations["query"] = operation;

        _serviceControl.operations = operations;
        try
        {
            _serviceControl.convertResultHandler = com.adobe.serializers.utility.TypeUtility.convertResultHandler;
        }
        catch (e: Error)
        { /* Flex 3.4 and eralier does not support the convertResultHandler functionality. */ }



        _serviceControl.service = "QueryService";
        _serviceControl.port = "Query";
        wsdl = "http://localhost:8093/exist/services/Query?WSDL";
        model_internal::loadWSDLIfNecessary();


        model_internal::initialize();
    }

    /**
      * This method is a generated wrapper used to call the 'disconnect' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function disconnect(sessionId:String) : mx.rpc.AsyncToken
    {
        model_internal::loadWSDLIfNecessary();
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("disconnect");
        var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(sessionId) ;

        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'listCollection' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function listCollection(sessionId:String, path:String) : mx.rpc.AsyncToken
    {
        model_internal::loadWSDLIfNecessary();
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("listCollection");
        var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(sessionId,path) ;

        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'getResource' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function getResource(sessionId:String, path:String, indent:Boolean, xinclude:Boolean) : mx.rpc.AsyncToken
    {
        model_internal::loadWSDLIfNecessary();
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("getResource");
        var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(sessionId,path,indent,xinclude) ;

        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'getResourceData' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function getResourceData(sessionId:String, path:String, indent:Boolean, xinclude:Boolean, processXSLPI:Boolean) : mx.rpc.AsyncToken
    {
        model_internal::loadWSDLIfNecessary();
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("getResourceData");
        var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(sessionId,path,indent,xinclude,processXSLPI) ;

        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'connect' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function connect(userId:String, password:String) : mx.rpc.AsyncToken
    {
        model_internal::loadWSDLIfNecessary();
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("connect");
        var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(userId,password) ;

        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'retrieve' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function retrieve(sessionId:String, start:int, howmany:int, indent:Boolean, xinclude:Boolean, highlight:String) : mx.rpc.AsyncToken
    {
        model_internal::loadWSDLIfNecessary();
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("retrieve");
        var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(sessionId,start,howmany,indent,xinclude,highlight) ;

        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'retrieveByDocument' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function retrieveByDocument(sessionId:String, start:int, howmany:int, path:String, indent:Boolean, xinclude:Boolean, highlight:String) : mx.rpc.AsyncToken
    {
        model_internal::loadWSDLIfNecessary();
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("retrieveByDocument");
        var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(sessionId,start,howmany,path,indent,xinclude,highlight) ;

        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'xquery' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function xquery(sessionId:String, xquery:ByteArray) : mx.rpc.AsyncToken
    {
        model_internal::loadWSDLIfNecessary();
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("xquery");
        var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(sessionId,xquery) ;

        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'retrieveData' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function retrieveData(sessionId:String, start:int, howmany:int, indent:Boolean, xinclude:Boolean, highlight:String) : mx.rpc.AsyncToken
    {
        model_internal::loadWSDLIfNecessary();
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("retrieveData");
        var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(sessionId,start,howmany,indent,xinclude,highlight) ;

        return _internal_token;
    }
     
    /**
      * This method is a generated wrapper used to call the 'query' operation. It returns an mx.rpc.AsyncToken whose 
      * result property will be populated with the result of the operation when the server response is received. 
      * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
      * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an mx.rpc.AsyncToken whose result property will be populated with the result of the operation when the server response is received.
      */
    public function query(sessionId:String, xpath:String) : mx.rpc.AsyncToken
    {
        model_internal::loadWSDLIfNecessary();
        var _internal_operation:mx.rpc.AbstractOperation = _serviceControl.getOperation("query");
        var _internal_token:mx.rpc.AsyncToken = _internal_operation.send(sessionId,xpath) ;

        return _internal_token;
    }
     
}

}
