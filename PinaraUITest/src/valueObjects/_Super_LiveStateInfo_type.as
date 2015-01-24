/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this value object you may modify the generated sub-class of this class - LiveStateInfo_type.as.
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

import flash.net.registerClassAlias;
import flash.net.getClassByAlias;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;

use namespace model_internal;

[ExcludeClass]
public class _Super_LiveStateInfo_type extends flash.events.EventDispatcher implements com.adobe.fiber.valueobjects.IValueObject
{
    model_internal static function initRemoteClassAliasSingle(cz:Class) : void
    {
    }

    model_internal static function initRemoteClassAliasAllRelated() : void
    {
    }

    model_internal var _dminternal_model : _LiveStateInfo_typeEntityMetadata;
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
    private var _internal_LSIDateTime : String;
    private var _internal_StateName : String;
    private var _internal_SubstateName : String;
    private var _internal_StatusName : String;

    private static var emptyArray:Array = new Array();


    /**
     * derived property cache initialization
     */
    model_internal var _cacheInitialized_isValid:Boolean = false;

    model_internal var _changeWatcherArray:Array = new Array();

    public function _Super_LiveStateInfo_type()
    {
        _model = new _LiveStateInfo_typeEntityMetadata(this);

        // Bind to own data or source properties for cache invalidation triggering
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "LSIDateTime", model_internal::setterListenerLSIDateTime));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "StateName", model_internal::setterListenerStateName));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "SubstateName", model_internal::setterListenerSubstateName));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "StatusName", model_internal::setterListenerStatusName));

    }

    /**
     * data/source property getters
     */

    [Bindable(event="propertyChange")]
    public function get LSIDateTime() : String
    {
        return _internal_LSIDateTime;
    }

    [Bindable(event="propertyChange")]
    public function get StateName() : String
    {
        return _internal_StateName;
    }

    [Bindable(event="propertyChange")]
    public function get SubstateName() : String
    {
        return _internal_SubstateName;
    }

    [Bindable(event="propertyChange")]
    public function get StatusName() : String
    {
        return _internal_StatusName;
    }

    public function clearAssociations() : void
    {
    }

    /**
     * data/source property setters
     */

    public function set LSIDateTime(value:String) : void
    {
        var oldValue:String = _internal_LSIDateTime;
        if (oldValue !== value)
        {
            _internal_LSIDateTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "LSIDateTime", oldValue, _internal_LSIDateTime));
        }
    }

    public function set StateName(value:String) : void
    {
        var oldValue:String = _internal_StateName;
        if (oldValue !== value)
        {
            _internal_StateName = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "StateName", oldValue, _internal_StateName));
        }
    }

    public function set SubstateName(value:String) : void
    {
        var oldValue:String = _internal_SubstateName;
        if (oldValue !== value)
        {
            _internal_SubstateName = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "SubstateName", oldValue, _internal_SubstateName));
        }
    }

    public function set StatusName(value:String) : void
    {
        var oldValue:String = _internal_StatusName;
        if (oldValue !== value)
        {
            _internal_StatusName = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "StatusName", oldValue, _internal_StatusName));
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

    model_internal function setterListenerLSIDateTime(value:flash.events.Event):void
    {
        _model.invalidateDependentOnLSIDateTime();
    }

    model_internal function setterListenerStateName(value:flash.events.Event):void
    {
        _model.invalidateDependentOnStateName();
    }

    model_internal function setterListenerSubstateName(value:flash.events.Event):void
    {
        _model.invalidateDependentOnSubstateName();
    }

    model_internal function setterListenerStatusName(value:flash.events.Event):void
    {
        _model.invalidateDependentOnStatusName();
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
        if (!_model.LSIDateTimeIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_LSIDateTimeValidationFailureMessages);
        }
        if (!_model.StateNameIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_StateNameValidationFailureMessages);
        }
        if (!_model.SubstateNameIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_SubstateNameValidationFailureMessages);
        }
        if (!_model.StatusNameIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_StatusNameValidationFailureMessages);
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
    public function get _model() : _LiveStateInfo_typeEntityMetadata
    {
        return model_internal::_dminternal_model;
    }

    public function set _model(value : _LiveStateInfo_typeEntityMetadata) : void
    {
        var oldValue : _LiveStateInfo_typeEntityMetadata = model_internal::_dminternal_model;
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

    model_internal var _doValidationCacheOfLSIDateTime : Array = null;
    model_internal var _doValidationLastValOfLSIDateTime : String;

    model_internal function _doValidationForLSIDateTime(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfLSIDateTime != null && model_internal::_doValidationLastValOfLSIDateTime == value)
           return model_internal::_doValidationCacheOfLSIDateTime ;

        _model.model_internal::_LSIDateTimeIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isLSIDateTimeAvailable && _internal_LSIDateTime == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "LSIDateTime is required"));
        }

        model_internal::_doValidationCacheOfLSIDateTime = validationFailures;
        model_internal::_doValidationLastValOfLSIDateTime = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfStateName : Array = null;
    model_internal var _doValidationLastValOfStateName : String;

    model_internal function _doValidationForStateName(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfStateName != null && model_internal::_doValidationLastValOfStateName == value)
           return model_internal::_doValidationCacheOfStateName ;

        _model.model_internal::_StateNameIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isStateNameAvailable && _internal_StateName == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "StateName is required"));
        }

        model_internal::_doValidationCacheOfStateName = validationFailures;
        model_internal::_doValidationLastValOfStateName = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfSubstateName : Array = null;
    model_internal var _doValidationLastValOfSubstateName : String;

    model_internal function _doValidationForSubstateName(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfSubstateName != null && model_internal::_doValidationLastValOfSubstateName == value)
           return model_internal::_doValidationCacheOfSubstateName ;

        _model.model_internal::_SubstateNameIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isSubstateNameAvailable && _internal_SubstateName == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "SubstateName is required"));
        }

        model_internal::_doValidationCacheOfSubstateName = validationFailures;
        model_internal::_doValidationLastValOfSubstateName = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfStatusName : Array = null;
    model_internal var _doValidationLastValOfStatusName : String;

    model_internal function _doValidationForStatusName(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfStatusName != null && model_internal::_doValidationLastValOfStatusName == value)
           return model_internal::_doValidationCacheOfStatusName ;

        _model.model_internal::_StatusNameIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isStatusNameAvailable && _internal_StatusName == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "StatusName is required"));
        }

        model_internal::_doValidationCacheOfStatusName = validationFailures;
        model_internal::_doValidationLastValOfStatusName = value;

        return validationFailures;
    }
    

}

}
