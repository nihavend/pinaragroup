/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this value object you may modify the generated sub-class of this class - Management.as.
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
import valueObjects.CascadingConditions;
import valueObjects.PeriodInfo;
import valueObjects.TimeManagement;

import flash.net.registerClassAlias;
import flash.net.getClassByAlias;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;

use namespace model_internal;

[ExcludeClass]
public class _Super_Management extends flash.events.EventDispatcher implements com.adobe.fiber.valueobjects.IValueObject
{
    model_internal static function initRemoteClassAliasSingle(cz:Class) : void
    {
    }

    model_internal static function initRemoteClassAliasAllRelated() : void
    {
        valueObjects.PeriodInfo.initRemoteClassAliasSingleChild();
        valueObjects.TimeManagement.initRemoteClassAliasSingleChild();
        valueObjects.BornedPlannedTime.initRemoteClassAliasSingleChild();
        valueObjects.JsPlannedTime.initRemoteClassAliasSingleChild();
        valueObjects.JsRealTime.initRemoteClassAliasSingleChild();
        valueObjects.JsTimeOut.initRemoteClassAliasSingleChild();
        valueObjects.CascadingConditions.initRemoteClassAliasSingleChild();
        valueObjects.JobAutoRetryInfo.initRemoteClassAliasSingleChild();
    }

    model_internal var _dminternal_model : _ManagementEntityMetadata;
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
    private var _internal_trigger : String;
    private var _internal_periodInfo : valueObjects.PeriodInfo;
    private var _internal_timeManagement : valueObjects.TimeManagement;
    private var _internal_cascadingConditions : valueObjects.CascadingConditions;

    private static var emptyArray:Array = new Array();


    /**
     * derived property cache initialization
     */
    model_internal var _cacheInitialized_isValid:Boolean = false;

    model_internal var _changeWatcherArray:Array = new Array();

    public function _Super_Management()
    {
        _model = new _ManagementEntityMetadata(this);

        // Bind to own data or source properties for cache invalidation triggering
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "trigger", model_internal::setterListenerTrigger));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "periodInfo", model_internal::setterListenerPeriodInfo));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "timeManagement", model_internal::setterListenerTimeManagement));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "cascadingConditions", model_internal::setterListenerCascadingConditions));

    }

    /**
     * data/source property getters
     */

    [Bindable(event="propertyChange")]
    public function get trigger() : String
    {
        return _internal_trigger;
    }

    [Bindable(event="propertyChange")]
    public function get periodInfo() : valueObjects.PeriodInfo
    {
        return _internal_periodInfo;
    }

    [Bindable(event="propertyChange")]
    public function get timeManagement() : valueObjects.TimeManagement
    {
        return _internal_timeManagement;
    }

    [Bindable(event="propertyChange")]
    public function get cascadingConditions() : valueObjects.CascadingConditions
    {
        return _internal_cascadingConditions;
    }

    public function clearAssociations() : void
    {
    }

    /**
     * data/source property setters
     */

    public function set trigger(value:String) : void
    {
        var oldValue:String = _internal_trigger;
        if (oldValue !== value)
        {
            _internal_trigger = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "trigger", oldValue, _internal_trigger));
        }
    }

    public function set periodInfo(value:valueObjects.PeriodInfo) : void
    {
        var oldValue:valueObjects.PeriodInfo = _internal_periodInfo;
        if (oldValue !== value)
        {
            _internal_periodInfo = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "periodInfo", oldValue, _internal_periodInfo));
        }
    }

    public function set timeManagement(value:valueObjects.TimeManagement) : void
    {
        var oldValue:valueObjects.TimeManagement = _internal_timeManagement;
        if (oldValue !== value)
        {
            _internal_timeManagement = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "timeManagement", oldValue, _internal_timeManagement));
        }
    }

    public function set cascadingConditions(value:valueObjects.CascadingConditions) : void
    {
        var oldValue:valueObjects.CascadingConditions = _internal_cascadingConditions;
        if (oldValue !== value)
        {
            _internal_cascadingConditions = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "cascadingConditions", oldValue, _internal_cascadingConditions));
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

    model_internal function setterListenerTrigger(value:flash.events.Event):void
    {
        _model.invalidateDependentOnTrigger();
    }

    model_internal function setterListenerPeriodInfo(value:flash.events.Event):void
    {
        _model.invalidateDependentOnPeriodInfo();
    }

    model_internal function setterListenerTimeManagement(value:flash.events.Event):void
    {
        _model.invalidateDependentOnTimeManagement();
    }

    model_internal function setterListenerCascadingConditions(value:flash.events.Event):void
    {
        _model.invalidateDependentOnCascadingConditions();
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
        if (!_model.triggerIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_triggerValidationFailureMessages);
        }
        if (!_model.periodInfoIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_periodInfoValidationFailureMessages);
        }
        if (!_model.timeManagementIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_timeManagementValidationFailureMessages);
        }
        if (!_model.cascadingConditionsIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_cascadingConditionsValidationFailureMessages);
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
    public function get _model() : _ManagementEntityMetadata
    {
        return model_internal::_dminternal_model;
    }

    public function set _model(value : _ManagementEntityMetadata) : void
    {
        var oldValue : _ManagementEntityMetadata = model_internal::_dminternal_model;
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

    model_internal var _doValidationCacheOfTrigger : Array = null;
    model_internal var _doValidationLastValOfTrigger : String;

    model_internal function _doValidationForTrigger(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfTrigger != null && model_internal::_doValidationLastValOfTrigger == value)
           return model_internal::_doValidationCacheOfTrigger ;

        _model.model_internal::_triggerIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isTriggerAvailable && _internal_trigger == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "trigger is required"));
        }

        model_internal::_doValidationCacheOfTrigger = validationFailures;
        model_internal::_doValidationLastValOfTrigger = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfPeriodInfo : Array = null;
    model_internal var _doValidationLastValOfPeriodInfo : valueObjects.PeriodInfo;

    model_internal function _doValidationForPeriodInfo(valueIn:Object):Array
    {
        var value : valueObjects.PeriodInfo = valueIn as valueObjects.PeriodInfo;

        if (model_internal::_doValidationCacheOfPeriodInfo != null && model_internal::_doValidationLastValOfPeriodInfo == value)
           return model_internal::_doValidationCacheOfPeriodInfo ;

        _model.model_internal::_periodInfoIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isPeriodInfoAvailable && _internal_periodInfo == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "periodInfo is required"));
        }

        model_internal::_doValidationCacheOfPeriodInfo = validationFailures;
        model_internal::_doValidationLastValOfPeriodInfo = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfTimeManagement : Array = null;
    model_internal var _doValidationLastValOfTimeManagement : valueObjects.TimeManagement;

    model_internal function _doValidationForTimeManagement(valueIn:Object):Array
    {
        var value : valueObjects.TimeManagement = valueIn as valueObjects.TimeManagement;

        if (model_internal::_doValidationCacheOfTimeManagement != null && model_internal::_doValidationLastValOfTimeManagement == value)
           return model_internal::_doValidationCacheOfTimeManagement ;

        _model.model_internal::_timeManagementIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isTimeManagementAvailable && _internal_timeManagement == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "timeManagement is required"));
        }

        model_internal::_doValidationCacheOfTimeManagement = validationFailures;
        model_internal::_doValidationLastValOfTimeManagement = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfCascadingConditions : Array = null;
    model_internal var _doValidationLastValOfCascadingConditions : valueObjects.CascadingConditions;

    model_internal function _doValidationForCascadingConditions(valueIn:Object):Array
    {
        var value : valueObjects.CascadingConditions = valueIn as valueObjects.CascadingConditions;

        if (model_internal::_doValidationCacheOfCascadingConditions != null && model_internal::_doValidationLastValOfCascadingConditions == value)
           return model_internal::_doValidationCacheOfCascadingConditions ;

        _model.model_internal::_cascadingConditionsIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isCascadingConditionsAvailable && _internal_cascadingConditions == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "cascadingConditions is required"));
        }

        model_internal::_doValidationCacheOfCascadingConditions = validationFailures;
        model_internal::_doValidationLastValOfCascadingConditions = value;

        return validationFailures;
    }
    

}

}
