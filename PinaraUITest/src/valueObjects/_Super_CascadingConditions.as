/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this value object you may modify the generated sub-class of this class - CascadingConditions.as.
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
import valueObjects.JobAutoRetryInfo;

import flash.net.registerClassAlias;
import flash.net.getClassByAlias;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;

use namespace model_internal;

[ExcludeClass]
public class _Super_CascadingConditions extends flash.events.EventDispatcher implements com.adobe.fiber.valueobjects.IValueObject
{
    model_internal static function initRemoteClassAliasSingle(cz:Class) : void
    {
    }

    model_internal static function initRemoteClassAliasAllRelated() : void
    {
        valueObjects.JobAutoRetryInfo.initRemoteClassAliasSingleChild();
    }

    model_internal var _dminternal_model : _CascadingConditionsEntityMetadata;
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
    private var _internal_runEvenIfFailed : String;
    private var _internal_jobSafeToRestart : String;
    private var _internal_jobAutoRetryInfo : valueObjects.JobAutoRetryInfo;

    private static var emptyArray:Array = new Array();


    /**
     * derived property cache initialization
     */
    model_internal var _cacheInitialized_isValid:Boolean = false;

    model_internal var _changeWatcherArray:Array = new Array();

    public function _Super_CascadingConditions()
    {
        _model = new _CascadingConditionsEntityMetadata(this);

        // Bind to own data or source properties for cache invalidation triggering
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "runEvenIfFailed", model_internal::setterListenerRunEvenIfFailed));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "jobSafeToRestart", model_internal::setterListenerJobSafeToRestart));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "jobAutoRetryInfo", model_internal::setterListenerJobAutoRetryInfo));

    }

    /**
     * data/source property getters
     */

    [Bindable(event="propertyChange")]
    public function get runEvenIfFailed() : String
    {
        return _internal_runEvenIfFailed;
    }

    [Bindable(event="propertyChange")]
    public function get jobSafeToRestart() : String
    {
        return _internal_jobSafeToRestart;
    }

    [Bindable(event="propertyChange")]
    public function get jobAutoRetryInfo() : valueObjects.JobAutoRetryInfo
    {
        return _internal_jobAutoRetryInfo;
    }

    public function clearAssociations() : void
    {
    }

    /**
     * data/source property setters
     */

    public function set runEvenIfFailed(value:String) : void
    {
        var oldValue:String = _internal_runEvenIfFailed;
        if (oldValue !== value)
        {
            _internal_runEvenIfFailed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "runEvenIfFailed", oldValue, _internal_runEvenIfFailed));
        }
    }

    public function set jobSafeToRestart(value:String) : void
    {
        var oldValue:String = _internal_jobSafeToRestart;
        if (oldValue !== value)
        {
            _internal_jobSafeToRestart = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobSafeToRestart", oldValue, _internal_jobSafeToRestart));
        }
    }

    public function set jobAutoRetryInfo(value:valueObjects.JobAutoRetryInfo) : void
    {
        var oldValue:valueObjects.JobAutoRetryInfo = _internal_jobAutoRetryInfo;
        if (oldValue !== value)
        {
            _internal_jobAutoRetryInfo = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobAutoRetryInfo", oldValue, _internal_jobAutoRetryInfo));
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

    model_internal function setterListenerRunEvenIfFailed(value:flash.events.Event):void
    {
        _model.invalidateDependentOnRunEvenIfFailed();
    }

    model_internal function setterListenerJobSafeToRestart(value:flash.events.Event):void
    {
        _model.invalidateDependentOnJobSafeToRestart();
    }

    model_internal function setterListenerJobAutoRetryInfo(value:flash.events.Event):void
    {
        _model.invalidateDependentOnJobAutoRetryInfo();
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
        if (!_model.runEvenIfFailedIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_runEvenIfFailedValidationFailureMessages);
        }
        if (!_model.jobSafeToRestartIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_jobSafeToRestartValidationFailureMessages);
        }
        if (!_model.jobAutoRetryInfoIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_jobAutoRetryInfoValidationFailureMessages);
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
    public function get _model() : _CascadingConditionsEntityMetadata
    {
        return model_internal::_dminternal_model;
    }

    public function set _model(value : _CascadingConditionsEntityMetadata) : void
    {
        var oldValue : _CascadingConditionsEntityMetadata = model_internal::_dminternal_model;
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

    model_internal var _doValidationCacheOfRunEvenIfFailed : Array = null;
    model_internal var _doValidationLastValOfRunEvenIfFailed : String;

    model_internal function _doValidationForRunEvenIfFailed(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfRunEvenIfFailed != null && model_internal::_doValidationLastValOfRunEvenIfFailed == value)
           return model_internal::_doValidationCacheOfRunEvenIfFailed ;

        _model.model_internal::_runEvenIfFailedIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isRunEvenIfFailedAvailable && _internal_runEvenIfFailed == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "runEvenIfFailed is required"));
        }

        model_internal::_doValidationCacheOfRunEvenIfFailed = validationFailures;
        model_internal::_doValidationLastValOfRunEvenIfFailed = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfJobSafeToRestart : Array = null;
    model_internal var _doValidationLastValOfJobSafeToRestart : String;

    model_internal function _doValidationForJobSafeToRestart(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfJobSafeToRestart != null && model_internal::_doValidationLastValOfJobSafeToRestart == value)
           return model_internal::_doValidationCacheOfJobSafeToRestart ;

        _model.model_internal::_jobSafeToRestartIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isJobSafeToRestartAvailable && _internal_jobSafeToRestart == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "jobSafeToRestart is required"));
        }

        model_internal::_doValidationCacheOfJobSafeToRestart = validationFailures;
        model_internal::_doValidationLastValOfJobSafeToRestart = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfJobAutoRetryInfo : Array = null;
    model_internal var _doValidationLastValOfJobAutoRetryInfo : valueObjects.JobAutoRetryInfo;

    model_internal function _doValidationForJobAutoRetryInfo(valueIn:Object):Array
    {
        var value : valueObjects.JobAutoRetryInfo = valueIn as valueObjects.JobAutoRetryInfo;

        if (model_internal::_doValidationCacheOfJobAutoRetryInfo != null && model_internal::_doValidationLastValOfJobAutoRetryInfo == value)
           return model_internal::_doValidationCacheOfJobAutoRetryInfo ;

        _model.model_internal::_jobAutoRetryInfoIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isJobAutoRetryInfoAvailable && _internal_jobAutoRetryInfo == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "jobAutoRetryInfo is required"));
        }

        model_internal::_doValidationCacheOfJobAutoRetryInfo = validationFailures;
        model_internal::_doValidationLastValOfJobAutoRetryInfo = value;

        return validationFailures;
    }
    

}

}
