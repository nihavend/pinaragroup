/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this value object you may modify the generated sub-class of this class - TimeManagement.as.
 */

package valueObjects
{
import com.adobe.fiber.services.IFiberManagingService;
import com.adobe.fiber.util.FiberUtils;
import com.adobe.fiber.valueobjects.IValueObject;
import flash.events.Event;
import flash.events.EventDispatcher;
import mx.binding.utils.ChangeWatcher;
import mx.collections.ArrayCollection;
import mx.events.PropertyChangeEvent;
import mx.validators.ValidationResult;
import valueObjects.BornedPlannedTime;
import valueObjects.JsPlannedTime;
import valueObjects.JsRealTime;
import valueObjects.JsTimeOut;

import flash.net.registerClassAlias;
import flash.net.getClassByAlias;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;

use namespace model_internal;

[ExcludeClass]
public class _Super_TimeManagement extends flash.events.EventDispatcher implements com.adobe.fiber.valueobjects.IValueObject
{
    model_internal static function initRemoteClassAliasSingle(cz:Class) : void
    {
    }

    model_internal static function initRemoteClassAliasAllRelated() : void
    {
        valueObjects.BornedPlannedTime.initRemoteClassAliasSingleChild();
        valueObjects.JsPlannedTime.initRemoteClassAliasSingleChild();
        valueObjects.JsRealTime.initRemoteClassAliasSingleChild();
        valueObjects.JsTimeOut.initRemoteClassAliasSingleChild();
    }

    model_internal var _dminternal_model : _TimeManagementEntityMetadata;
    model_internal var _changedObjects:mx.collections.ArrayCollection = new ArrayCollection();

    public function getChangedObjects() : Array
    {
        _changedObjects.addItemAt(this,0);
        return _changedObjects.source;
    }

    public function clearChangedObjects() : void
    {
        _changedObjects.removeAll();
    }

    /**
     * properties
     */
    private var _internal_bornedPlannedTime : valueObjects.BornedPlannedTime;
    private var _internal_jsPlannedTime : valueObjects.JsPlannedTime;
    private var _internal_jsRealTime : valueObjects.JsRealTime;
    private var _internal_jsTimeOut : valueObjects.JsTimeOut;
    private var _internal_expectedDuration : String;
    private var _internal_previousDuration : String;
    private var _internal_realizedDuration : String;

    private static var emptyArray:Array = new Array();


    /**
     * derived property cache initialization
     */
    model_internal var _cacheInitialized_isValid:Boolean = false;

    model_internal var _changeWatcherArray:Array = new Array();

    public function _Super_TimeManagement()
    {
        _model = new _TimeManagementEntityMetadata(this);

        // Bind to own data or source properties for cache invalidation triggering
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "bornedPlannedTime", model_internal::setterListenerBornedPlannedTime));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "jsPlannedTime", model_internal::setterListenerJsPlannedTime));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "jsRealTime", model_internal::setterListenerJsRealTime));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "jsTimeOut", model_internal::setterListenerJsTimeOut));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "expectedDuration", model_internal::setterListenerExpectedDuration));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "previousDuration", model_internal::setterListenerPreviousDuration));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "realizedDuration", model_internal::setterListenerRealizedDuration));

    }

    /**
     * data/source property getters
     */

    [Bindable(event="propertyChange")]
    public function get bornedPlannedTime() : valueObjects.BornedPlannedTime
    {
        return _internal_bornedPlannedTime;
    }

    [Bindable(event="propertyChange")]
    public function get jsPlannedTime() : valueObjects.JsPlannedTime
    {
        return _internal_jsPlannedTime;
    }

    [Bindable(event="propertyChange")]
    public function get jsRealTime() : valueObjects.JsRealTime
    {
        return _internal_jsRealTime;
    }

    [Bindable(event="propertyChange")]
    public function get jsTimeOut() : valueObjects.JsTimeOut
    {
        return _internal_jsTimeOut;
    }

    [Bindable(event="propertyChange")]
    public function get expectedDuration() : String
    {
        return _internal_expectedDuration;
    }

    [Bindable(event="propertyChange")]
    public function get previousDuration() : String
    {
        return _internal_previousDuration;
    }

    [Bindable(event="propertyChange")]
    public function get realizedDuration() : String
    {
        return _internal_realizedDuration;
    }

    public function clearAssociations() : void
    {
    }

    /**
     * data/source property setters
     */

    public function set bornedPlannedTime(value:valueObjects.BornedPlannedTime) : void
    {
        var oldValue:valueObjects.BornedPlannedTime = _internal_bornedPlannedTime;
        if (oldValue !== value)
        {
            _internal_bornedPlannedTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "bornedPlannedTime", oldValue, _internal_bornedPlannedTime));
        }
    }

    public function set jsPlannedTime(value:valueObjects.JsPlannedTime) : void
    {
        var oldValue:valueObjects.JsPlannedTime = _internal_jsPlannedTime;
        if (oldValue !== value)
        {
            _internal_jsPlannedTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsPlannedTime", oldValue, _internal_jsPlannedTime));
        }
    }

    public function set jsRealTime(value:valueObjects.JsRealTime) : void
    {
        var oldValue:valueObjects.JsRealTime = _internal_jsRealTime;
        if (oldValue !== value)
        {
            _internal_jsRealTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsRealTime", oldValue, _internal_jsRealTime));
        }
    }

    public function set jsTimeOut(value:valueObjects.JsTimeOut) : void
    {
        var oldValue:valueObjects.JsTimeOut = _internal_jsTimeOut;
        if (oldValue !== value)
        {
            _internal_jsTimeOut = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsTimeOut", oldValue, _internal_jsTimeOut));
        }
    }

    public function set expectedDuration(value:String) : void
    {
        var oldValue:String = _internal_expectedDuration;
        if (oldValue !== value)
        {
            _internal_expectedDuration = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "expectedDuration", oldValue, _internal_expectedDuration));
        }
    }

    public function set previousDuration(value:String) : void
    {
        var oldValue:String = _internal_previousDuration;
        if (oldValue !== value)
        {
            _internal_previousDuration = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "previousDuration", oldValue, _internal_previousDuration));
        }
    }

    public function set realizedDuration(value:String) : void
    {
        var oldValue:String = _internal_realizedDuration;
        if (oldValue !== value)
        {
            _internal_realizedDuration = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "realizedDuration", oldValue, _internal_realizedDuration));
        }
    }

    /**
     * Data/source property setter listeners
     *
     * Each data property whose value affects other properties or the validity of the entity
     * needs to invalidate all previously calculated artifacts. These include:
     *  - any derived properties or constraints that reference the given data property.
     *  - any availability guards (variant expressions) that reference the given data property.
     *  - any style validations, message tokens or guards that reference the given data property.
     *  - the validity of the property (and the containing entity) if the given data property has a length restriction.
     *  - the validity of the property (and the containing entity) if the given data property is required.
     */

    model_internal function setterListenerBornedPlannedTime(value:flash.events.Event):void
    {
        _model.invalidateDependentOnBornedPlannedTime();
    }

    model_internal function setterListenerJsPlannedTime(value:flash.events.Event):void
    {
        _model.invalidateDependentOnJsPlannedTime();
    }

    model_internal function setterListenerJsRealTime(value:flash.events.Event):void
    {
        _model.invalidateDependentOnJsRealTime();
    }

    model_internal function setterListenerJsTimeOut(value:flash.events.Event):void
    {
        _model.invalidateDependentOnJsTimeOut();
    }

    model_internal function setterListenerExpectedDuration(value:flash.events.Event):void
    {
        _model.invalidateDependentOnExpectedDuration();
    }

    model_internal function setterListenerPreviousDuration(value:flash.events.Event):void
    {
        _model.invalidateDependentOnPreviousDuration();
    }

    model_internal function setterListenerRealizedDuration(value:flash.events.Event):void
    {
        _model.invalidateDependentOnRealizedDuration();
    }


    /**
     * valid related derived properties
     */
    model_internal var _isValid : Boolean;
    model_internal var _invalidConstraints:Array = new Array();
    model_internal var _validationFailureMessages:Array = new Array();

    /**
     * derived property calculators
     */

    /**
     * isValid calculator
     */
    model_internal function calculateIsValid():Boolean
    {
        var violatedConsts:Array = new Array();
        var validationFailureMessages:Array = new Array();

        var propertyValidity:Boolean = true;
        if (!_model.bornedPlannedTimeIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_bornedPlannedTimeValidationFailureMessages);
        }
        if (!_model.jsPlannedTimeIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_jsPlannedTimeValidationFailureMessages);
        }
        if (!_model.jsRealTimeIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_jsRealTimeValidationFailureMessages);
        }
        if (!_model.jsTimeOutIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_jsTimeOutValidationFailureMessages);
        }
        if (!_model.expectedDurationIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_expectedDurationValidationFailureMessages);
        }
        if (!_model.previousDurationIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_previousDurationValidationFailureMessages);
        }
        if (!_model.realizedDurationIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_realizedDurationValidationFailureMessages);
        }

        model_internal::_cacheInitialized_isValid = true;
        model_internal::invalidConstraints_der = violatedConsts;
        model_internal::validationFailureMessages_der = validationFailureMessages;
        return violatedConsts.length == 0 && propertyValidity;
    }

    /**
     * derived property setters
     */

    model_internal function set isValid_der(value:Boolean) : void
    {
        var oldValue:Boolean = model_internal::_isValid;
        if (oldValue !== value)
        {
            model_internal::_isValid = value;
            _model.model_internal::fireChangeEvent("isValid", oldValue, model_internal::_isValid);
        }
    }

    /**
     * derived property getters
     */

    [Transient]
    [Bindable(event="propertyChange")]
    public function get _model() : _TimeManagementEntityMetadata
    {
        return model_internal::_dminternal_model;
    }

    public function set _model(value : _TimeManagementEntityMetadata) : void
    {
        var oldValue : _TimeManagementEntityMetadata = model_internal::_dminternal_model;
        if (oldValue !== value)
        {
            model_internal::_dminternal_model = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "_model", oldValue, model_internal::_dminternal_model));
        }
    }

    /**
     * methods
     */


    /**
     *  services
     */
    private var _managingService:com.adobe.fiber.services.IFiberManagingService;

    public function set managingService(managingService:com.adobe.fiber.services.IFiberManagingService):void
    {
        _managingService = managingService;
    }

    model_internal function set invalidConstraints_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_invalidConstraints;
        // avoid firing the event when old and new value are different empty arrays
        if (oldValue !== value && (oldValue.length > 0 || value.length > 0))
        {
            model_internal::_invalidConstraints = value;
            _model.model_internal::fireChangeEvent("invalidConstraints", oldValue, model_internal::_invalidConstraints);
        }
    }

    model_internal function set validationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_validationFailureMessages;
        // avoid firing the event when old and new value are different empty arrays
        if (oldValue !== value && (oldValue.length > 0 || value.length > 0))
        {
            model_internal::_validationFailureMessages = value;
            _model.model_internal::fireChangeEvent("validationFailureMessages", oldValue, model_internal::_validationFailureMessages);
        }
    }

    model_internal var _doValidationCacheOfBornedPlannedTime : Array = null;
    model_internal var _doValidationLastValOfBornedPlannedTime : valueObjects.BornedPlannedTime;

    model_internal function _doValidationForBornedPlannedTime(valueIn:Object):Array
    {
        var value : valueObjects.BornedPlannedTime = valueIn as valueObjects.BornedPlannedTime;

        if (model_internal::_doValidationCacheOfBornedPlannedTime != null && model_internal::_doValidationLastValOfBornedPlannedTime == value)
           return model_internal::_doValidationCacheOfBornedPlannedTime ;

        _model.model_internal::_bornedPlannedTimeIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isBornedPlannedTimeAvailable && _internal_bornedPlannedTime == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "bornedPlannedTime is required"));
        }

        model_internal::_doValidationCacheOfBornedPlannedTime = validationFailures;
        model_internal::_doValidationLastValOfBornedPlannedTime = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfJsPlannedTime : Array = null;
    model_internal var _doValidationLastValOfJsPlannedTime : valueObjects.JsPlannedTime;

    model_internal function _doValidationForJsPlannedTime(valueIn:Object):Array
    {
        var value : valueObjects.JsPlannedTime = valueIn as valueObjects.JsPlannedTime;

        if (model_internal::_doValidationCacheOfJsPlannedTime != null && model_internal::_doValidationLastValOfJsPlannedTime == value)
           return model_internal::_doValidationCacheOfJsPlannedTime ;

        _model.model_internal::_jsPlannedTimeIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isJsPlannedTimeAvailable && _internal_jsPlannedTime == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "jsPlannedTime is required"));
        }

        model_internal::_doValidationCacheOfJsPlannedTime = validationFailures;
        model_internal::_doValidationLastValOfJsPlannedTime = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfJsRealTime : Array = null;
    model_internal var _doValidationLastValOfJsRealTime : valueObjects.JsRealTime;

    model_internal function _doValidationForJsRealTime(valueIn:Object):Array
    {
        var value : valueObjects.JsRealTime = valueIn as valueObjects.JsRealTime;

        if (model_internal::_doValidationCacheOfJsRealTime != null && model_internal::_doValidationLastValOfJsRealTime == value)
           return model_internal::_doValidationCacheOfJsRealTime ;

        _model.model_internal::_jsRealTimeIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isJsRealTimeAvailable && _internal_jsRealTime == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "jsRealTime is required"));
        }

        model_internal::_doValidationCacheOfJsRealTime = validationFailures;
        model_internal::_doValidationLastValOfJsRealTime = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfJsTimeOut : Array = null;
    model_internal var _doValidationLastValOfJsTimeOut : valueObjects.JsTimeOut;

    model_internal function _doValidationForJsTimeOut(valueIn:Object):Array
    {
        var value : valueObjects.JsTimeOut = valueIn as valueObjects.JsTimeOut;

        if (model_internal::_doValidationCacheOfJsTimeOut != null && model_internal::_doValidationLastValOfJsTimeOut == value)
           return model_internal::_doValidationCacheOfJsTimeOut ;

        _model.model_internal::_jsTimeOutIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isJsTimeOutAvailable && _internal_jsTimeOut == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "jsTimeOut is required"));
        }

        model_internal::_doValidationCacheOfJsTimeOut = validationFailures;
        model_internal::_doValidationLastValOfJsTimeOut = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfExpectedDuration : Array = null;
    model_internal var _doValidationLastValOfExpectedDuration : String;

    model_internal function _doValidationForExpectedDuration(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfExpectedDuration != null && model_internal::_doValidationLastValOfExpectedDuration == value)
           return model_internal::_doValidationCacheOfExpectedDuration ;

        _model.model_internal::_expectedDurationIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isExpectedDurationAvailable && _internal_expectedDuration == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "expectedDuration is required"));
        }

        model_internal::_doValidationCacheOfExpectedDuration = validationFailures;
        model_internal::_doValidationLastValOfExpectedDuration = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfPreviousDuration : Array = null;
    model_internal var _doValidationLastValOfPreviousDuration : String;

    model_internal function _doValidationForPreviousDuration(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfPreviousDuration != null && model_internal::_doValidationLastValOfPreviousDuration == value)
           return model_internal::_doValidationCacheOfPreviousDuration ;

        _model.model_internal::_previousDurationIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isPreviousDurationAvailable && _internal_previousDuration == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "previousDuration is required"));
        }

        model_internal::_doValidationCacheOfPreviousDuration = validationFailures;
        model_internal::_doValidationLastValOfPreviousDuration = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfRealizedDuration : Array = null;
    model_internal var _doValidationLastValOfRealizedDuration : String;

    model_internal function _doValidationForRealizedDuration(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfRealizedDuration != null && model_internal::_doValidationLastValOfRealizedDuration == value)
           return model_internal::_doValidationCacheOfRealizedDuration ;

        _model.model_internal::_realizedDurationIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isRealizedDurationAvailable && _internal_realizedDuration == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "realizedDuration is required"));
        }

        model_internal::_doValidationCacheOfRealizedDuration = validationFailures;
        model_internal::_doValidationLastValOfRealizedDuration = value;

        return validationFailures;
    }
    

}

}
