/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this value object you may modify the generated sub-class of this class - JobTypeDetails.as.
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
public class _Super_JobTypeDetails extends flash.events.EventDispatcher implements com.adobe.fiber.valueobjects.IValueObject
{
    model_internal static function initRemoteClassAliasSingle(cz:Class) : void
    {
    }

    model_internal static function initRemoteClassAliasAllRelated() : void
    {
    }

    model_internal var _dminternal_model : _JobTypeDetailsEntityMetadata;
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
    private var _internal_jobCommandType : String;
    private var _internal_jobCommand : String;
    private var _internal_jobPath : String;

    private static var emptyArray:Array = new Array();


    /**
     * derived property cache initialization
     */
    model_internal var _cacheInitialized_isValid:Boolean = false;

    model_internal var _changeWatcherArray:Array = new Array();

    public function _Super_JobTypeDetails()
    {
        _model = new _JobTypeDetailsEntityMetadata(this);

        // Bind to own data or source properties for cache invalidation triggering
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "jobCommandType", model_internal::setterListenerJobCommandType));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "jobCommand", model_internal::setterListenerJobCommand));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "jobPath", model_internal::setterListenerJobPath));

    }

    /**
     * data/source property getters
     */

    [Bindable(event="propertyChange")]
    public function get jobCommandType() : String
    {
        return _internal_jobCommandType;
    }

    [Bindable(event="propertyChange")]
    public function get jobCommand() : String
    {
        return _internal_jobCommand;
    }

    [Bindable(event="propertyChange")]
    public function get jobPath() : String
    {
        return _internal_jobPath;
    }

    public function clearAssociations() : void
    {
    }

    /**
     * data/source property setters
     */

    public function set jobCommandType(value:String) : void
    {
        var oldValue:String = _internal_jobCommandType;
        if (oldValue !== value)
        {
            _internal_jobCommandType = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobCommandType", oldValue, _internal_jobCommandType));
        }
    }

    public function set jobCommand(value:String) : void
    {
        var oldValue:String = _internal_jobCommand;
        if (oldValue !== value)
        {
            _internal_jobCommand = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobCommand", oldValue, _internal_jobCommand));
        }
    }

    public function set jobPath(value:String) : void
    {
        var oldValue:String = _internal_jobPath;
        if (oldValue !== value)
        {
            _internal_jobPath = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobPath", oldValue, _internal_jobPath));
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

    model_internal function setterListenerJobCommandType(value:flash.events.Event):void
    {
        _model.invalidateDependentOnJobCommandType();
    }

    model_internal function setterListenerJobCommand(value:flash.events.Event):void
    {
        _model.invalidateDependentOnJobCommand();
    }

    model_internal function setterListenerJobPath(value:flash.events.Event):void
    {
        _model.invalidateDependentOnJobPath();
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
        if (!_model.jobCommandTypeIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_jobCommandTypeValidationFailureMessages);
        }
        if (!_model.jobCommandIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_jobCommandValidationFailureMessages);
        }
        if (!_model.jobPathIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_jobPathValidationFailureMessages);
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
    public function get _model() : _JobTypeDetailsEntityMetadata
    {
        return model_internal::_dminternal_model;
    }

    public function set _model(value : _JobTypeDetailsEntityMetadata) : void
    {
        var oldValue : _JobTypeDetailsEntityMetadata = model_internal::_dminternal_model;
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

    model_internal var _doValidationCacheOfJobCommandType : Array = null;
    model_internal var _doValidationLastValOfJobCommandType : String;

    model_internal function _doValidationForJobCommandType(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfJobCommandType != null && model_internal::_doValidationLastValOfJobCommandType == value)
           return model_internal::_doValidationCacheOfJobCommandType ;

        _model.model_internal::_jobCommandTypeIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isJobCommandTypeAvailable && _internal_jobCommandType == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "jobCommandType is required"));
        }

        model_internal::_doValidationCacheOfJobCommandType = validationFailures;
        model_internal::_doValidationLastValOfJobCommandType = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfJobCommand : Array = null;
    model_internal var _doValidationLastValOfJobCommand : String;

    model_internal function _doValidationForJobCommand(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfJobCommand != null && model_internal::_doValidationLastValOfJobCommand == value)
           return model_internal::_doValidationCacheOfJobCommand ;

        _model.model_internal::_jobCommandIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isJobCommandAvailable && _internal_jobCommand == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "jobCommand is required"));
        }

        model_internal::_doValidationCacheOfJobCommand = validationFailures;
        model_internal::_doValidationLastValOfJobCommand = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfJobPath : Array = null;
    model_internal var _doValidationLastValOfJobPath : String;

    model_internal function _doValidationForJobPath(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfJobPath != null && model_internal::_doValidationLastValOfJobPath == value)
           return model_internal::_doValidationCacheOfJobPath ;

        _model.model_internal::_jobPathIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isJobPathAvailable && _internal_jobPath == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "jobPath is required"));
        }

        model_internal::_doValidationCacheOfJobPath = validationFailures;
        model_internal::_doValidationLastValOfJobPath = value;

        return validationFailures;
    }
    

}

}
