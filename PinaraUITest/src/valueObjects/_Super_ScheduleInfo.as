/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this value object you may modify the generated sub-class of this class - ScheduleInfo.as.
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
import mx.events.CollectionEvent;
import mx.events.PropertyChangeEvent;
import mx.validators.ValidationResult;
import valueObjects.DaysOfMonth;

import flash.net.registerClassAlias;
import flash.net.getClassByAlias;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;

use namespace model_internal;

[ExcludeClass]
public class _Super_ScheduleInfo extends flash.events.EventDispatcher implements com.adobe.fiber.valueobjects.IValueObject
{
    model_internal static function initRemoteClassAliasSingle(cz:Class) : void
    {
    }

    model_internal static function initRemoteClassAliasAllRelated() : void
    {
        valueObjects.DaysOfMonth.initRemoteClassAliasSingleChild();
    }

    model_internal var _dminternal_model : _ScheduleInfoEntityMetadata;
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
    private var _internal_daysOfMonth : valueObjects.DaysOfMonth;
    private var _internal_daysOfWeekIntType : ArrayCollection;

    private static var emptyArray:Array = new Array();


    /**
     * derived property cache initialization
     */
    model_internal var _cacheInitialized_isValid:Boolean = false;

    model_internal var _changeWatcherArray:Array = new Array();

    public function _Super_ScheduleInfo()
    {
        _model = new _ScheduleInfoEntityMetadata(this);

        // Bind to own data or source properties for cache invalidation triggering
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "daysOfMonth", model_internal::setterListenerDaysOfMonth));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "daysOfWeekIntType", model_internal::setterListenerDaysOfWeekIntType));

    }

    /**
     * data/source property getters
     */

    [Bindable(event="propertyChange")]
    public function get daysOfMonth() : valueObjects.DaysOfMonth
    {
        return _internal_daysOfMonth;
    }

    [Bindable(event="propertyChange")]
    public function get daysOfWeekIntType() : ArrayCollection
    {
        return _internal_daysOfWeekIntType;
    }

    public function clearAssociations() : void
    {
    }

    /**
     * data/source property setters
     */

    public function set daysOfMonth(value:valueObjects.DaysOfMonth) : void
    {
        var oldValue:valueObjects.DaysOfMonth = _internal_daysOfMonth;
        if (oldValue !== value)
        {
            _internal_daysOfMonth = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "daysOfMonth", oldValue, _internal_daysOfMonth));
        }
    }

    public function set daysOfWeekIntType(value:*) : void
    {
        var oldValue:ArrayCollection = _internal_daysOfWeekIntType;
        if (oldValue !== value)
        {
            if (value is ArrayCollection)
            {
                _internal_daysOfWeekIntType = value;
            }
            else if (value is Array)
            {
                _internal_daysOfWeekIntType = new ArrayCollection(value);
            }
            else if (value == null)
            {
                _internal_daysOfWeekIntType = null;
            }
            else
            {
                throw new Error("value of daysOfWeekIntType must be a collection");
            }
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "daysOfWeekIntType", oldValue, _internal_daysOfWeekIntType));
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

    model_internal function setterListenerDaysOfMonth(value:flash.events.Event):void
    {
        _model.invalidateDependentOnDaysOfMonth();
    }

    model_internal function setterListenerDaysOfWeekIntType(value:flash.events.Event):void
    {
        if (value is mx.events.PropertyChangeEvent)
        {
            if (mx.events.PropertyChangeEvent(value).newValue)
            {
                mx.events.PropertyChangeEvent(value).newValue.addEventListener(mx.events.CollectionEvent.COLLECTION_CHANGE, model_internal::setterListenerDaysOfWeekIntType);
            }
        }
        _model.invalidateDependentOnDaysOfWeekIntType();
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
        if (!_model.daysOfMonthIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_daysOfMonthValidationFailureMessages);
        }
        if (!_model.daysOfWeekIntTypeIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_daysOfWeekIntTypeValidationFailureMessages);
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
    public function get _model() : _ScheduleInfoEntityMetadata
    {
        return model_internal::_dminternal_model;
    }

    public function set _model(value : _ScheduleInfoEntityMetadata) : void
    {
        var oldValue : _ScheduleInfoEntityMetadata = model_internal::_dminternal_model;
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

    model_internal var _doValidationCacheOfDaysOfMonth : Array = null;
    model_internal var _doValidationLastValOfDaysOfMonth : valueObjects.DaysOfMonth;

    model_internal function _doValidationForDaysOfMonth(valueIn:Object):Array
    {
        var value : valueObjects.DaysOfMonth = valueIn as valueObjects.DaysOfMonth;

        if (model_internal::_doValidationCacheOfDaysOfMonth != null && model_internal::_doValidationLastValOfDaysOfMonth == value)
           return model_internal::_doValidationCacheOfDaysOfMonth ;

        _model.model_internal::_daysOfMonthIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isDaysOfMonthAvailable && _internal_daysOfMonth == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "daysOfMonth is required"));
        }

        model_internal::_doValidationCacheOfDaysOfMonth = validationFailures;
        model_internal::_doValidationLastValOfDaysOfMonth = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfDaysOfWeekIntType : Array = null;
    model_internal var _doValidationLastValOfDaysOfWeekIntType : ArrayCollection;

    model_internal function _doValidationForDaysOfWeekIntType(valueIn:Object):Array
    {
        var value : ArrayCollection = valueIn as ArrayCollection;

        if (model_internal::_doValidationCacheOfDaysOfWeekIntType != null && model_internal::_doValidationLastValOfDaysOfWeekIntType == value)
           return model_internal::_doValidationCacheOfDaysOfWeekIntType ;

        _model.model_internal::_daysOfWeekIntTypeIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isDaysOfWeekIntTypeAvailable && _internal_daysOfWeekIntType == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "daysOfWeekIntType is required"));
        }

        model_internal::_doValidationCacheOfDaysOfWeekIntType = validationFailures;
        model_internal::_doValidationLastValOfDaysOfWeekIntType = value;

        return validationFailures;
    }
    

}

}
