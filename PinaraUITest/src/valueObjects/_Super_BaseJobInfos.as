/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this value object you may modify the generated sub-class of this class - BaseJobInfos.as.
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
import valueObjects.JobTypeDetails;

import flash.net.registerClassAlias;
import flash.net.getClassByAlias;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;

use namespace model_internal;

[ExcludeClass]
public class _Super_BaseJobInfos extends flash.events.EventDispatcher implements com.adobe.fiber.valueobjects.IValueObject
{
    model_internal static function initRemoteClassAliasSingle(cz:Class) : void
    {
    }

    model_internal static function initRemoteClassAliasAllRelated() : void
    {
        valueObjects.JobTypeDetails.initRemoteClassAliasSingleChild();
    }

    model_internal var _dminternal_model : _BaseJobInfosEntityMetadata;
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
    private var _internal_jsName : String;
    private var _internal_jobTypeDetails : valueObjects.JobTypeDetails;
    private var _internal_jobLogFile : String;
    private var _internal_jobLogPath : String;
    private var _internal_oSystem : String;
    private var _internal_jobPriority : String;
    private var _internal_jsIsActive : String;
    private var _internal_userId : String;

    private static var emptyArray:Array = new Array();


    /**
     * derived property cache initialization
     */
    model_internal var _cacheInitialized_isValid:Boolean = false;

    model_internal var _changeWatcherArray:Array = new Array();

    public function _Super_BaseJobInfos()
    {
        _model = new _BaseJobInfosEntityMetadata(this);

        // Bind to own data or source properties for cache invalidation triggering
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "jsName", model_internal::setterListenerJsName));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "jobTypeDetails", model_internal::setterListenerJobTypeDetails));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "jobLogFile", model_internal::setterListenerJobLogFile));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "jobLogPath", model_internal::setterListenerJobLogPath));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "oSystem", model_internal::setterListenerOSystem));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "jobPriority", model_internal::setterListenerJobPriority));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "jsIsActive", model_internal::setterListenerJsIsActive));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "userId", model_internal::setterListenerUserId));

    }

    /**
     * data/source property getters
     */

    [Bindable(event="propertyChange")]
    public function get jsName() : String
    {
        return _internal_jsName;
    }

    [Bindable(event="propertyChange")]
    public function get jobTypeDetails() : valueObjects.JobTypeDetails
    {
        return _internal_jobTypeDetails;
    }

    [Bindable(event="propertyChange")]
    public function get jobLogFile() : String
    {
        return _internal_jobLogFile;
    }

    [Bindable(event="propertyChange")]
    public function get jobLogPath() : String
    {
        return _internal_jobLogPath;
    }

    [Bindable(event="propertyChange")]
    public function get oSystem() : String
    {
        return _internal_oSystem;
    }

    [Bindable(event="propertyChange")]
    public function get jobPriority() : String
    {
        return _internal_jobPriority;
    }

    [Bindable(event="propertyChange")]
    public function get jsIsActive() : String
    {
        return _internal_jsIsActive;
    }

    [Bindable(event="propertyChange")]
    public function get userId() : String
    {
        return _internal_userId;
    }

    public function clearAssociations() : void
    {
    }

    /**
     * data/source property setters
     */

    public function set jsName(value:String) : void
    {
        var oldValue:String = _internal_jsName;
        if (oldValue !== value)
        {
            _internal_jsName = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsName", oldValue, _internal_jsName));
        }
    }

    public function set jobTypeDetails(value:valueObjects.JobTypeDetails) : void
    {
        var oldValue:valueObjects.JobTypeDetails = _internal_jobTypeDetails;
        if (oldValue !== value)
        {
            _internal_jobTypeDetails = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobTypeDetails", oldValue, _internal_jobTypeDetails));
        }
    }

    public function set jobLogFile(value:String) : void
    {
        var oldValue:String = _internal_jobLogFile;
        if (oldValue !== value)
        {
            _internal_jobLogFile = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobLogFile", oldValue, _internal_jobLogFile));
        }
    }

    public function set jobLogPath(value:String) : void
    {
        var oldValue:String = _internal_jobLogPath;
        if (oldValue !== value)
        {
            _internal_jobLogPath = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobLogPath", oldValue, _internal_jobLogPath));
        }
    }

    public function set oSystem(value:String) : void
    {
        var oldValue:String = _internal_oSystem;
        if (oldValue !== value)
        {
            _internal_oSystem = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "oSystem", oldValue, _internal_oSystem));
        }
    }

    public function set jobPriority(value:String) : void
    {
        var oldValue:String = _internal_jobPriority;
        if (oldValue !== value)
        {
            _internal_jobPriority = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobPriority", oldValue, _internal_jobPriority));
        }
    }

    public function set jsIsActive(value:String) : void
    {
        var oldValue:String = _internal_jsIsActive;
        if (oldValue !== value)
        {
            _internal_jsIsActive = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsIsActive", oldValue, _internal_jsIsActive));
        }
    }

    public function set userId(value:String) : void
    {
        var oldValue:String = _internal_userId;
        if (oldValue !== value)
        {
            _internal_userId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "userId", oldValue, _internal_userId));
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

    model_internal function setterListenerJsName(value:flash.events.Event):void
    {
        _model.invalidateDependentOnJsName();
    }

    model_internal function setterListenerJobTypeDetails(value:flash.events.Event):void
    {
        _model.invalidateDependentOnJobTypeDetails();
    }

    model_internal function setterListenerJobLogFile(value:flash.events.Event):void
    {
        _model.invalidateDependentOnJobLogFile();
    }

    model_internal function setterListenerJobLogPath(value:flash.events.Event):void
    {
        _model.invalidateDependentOnJobLogPath();
    }

    model_internal function setterListenerOSystem(value:flash.events.Event):void
    {
        _model.invalidateDependentOnOSystem();
    }

    model_internal function setterListenerJobPriority(value:flash.events.Event):void
    {
        _model.invalidateDependentOnJobPriority();
    }

    model_internal function setterListenerJsIsActive(value:flash.events.Event):void
    {
        _model.invalidateDependentOnJsIsActive();
    }

    model_internal function setterListenerUserId(value:flash.events.Event):void
    {
        _model.invalidateDependentOnUserId();
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
        if (!_model.jsNameIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_jsNameValidationFailureMessages);
        }
        if (!_model.jobTypeDetailsIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_jobTypeDetailsValidationFailureMessages);
        }
        if (!_model.jobLogFileIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_jobLogFileValidationFailureMessages);
        }
        if (!_model.jobLogPathIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_jobLogPathValidationFailureMessages);
        }
        if (!_model.oSystemIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_oSystemValidationFailureMessages);
        }
        if (!_model.jobPriorityIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_jobPriorityValidationFailureMessages);
        }
        if (!_model.jsIsActiveIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_jsIsActiveValidationFailureMessages);
        }
        if (!_model.userIdIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_userIdValidationFailureMessages);
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
    public function get _model() : _BaseJobInfosEntityMetadata
    {
        return model_internal::_dminternal_model;
    }

    public function set _model(value : _BaseJobInfosEntityMetadata) : void
    {
        var oldValue : _BaseJobInfosEntityMetadata = model_internal::_dminternal_model;
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

    model_internal var _doValidationCacheOfJsName : Array = null;
    model_internal var _doValidationLastValOfJsName : String;

    model_internal function _doValidationForJsName(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfJsName != null && model_internal::_doValidationLastValOfJsName == value)
           return model_internal::_doValidationCacheOfJsName ;

        _model.model_internal::_jsNameIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isJsNameAvailable && _internal_jsName == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "jsName is required"));
        }

        model_internal::_doValidationCacheOfJsName = validationFailures;
        model_internal::_doValidationLastValOfJsName = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfJobTypeDetails : Array = null;
    model_internal var _doValidationLastValOfJobTypeDetails : valueObjects.JobTypeDetails;

    model_internal function _doValidationForJobTypeDetails(valueIn:Object):Array
    {
        var value : valueObjects.JobTypeDetails = valueIn as valueObjects.JobTypeDetails;

        if (model_internal::_doValidationCacheOfJobTypeDetails != null && model_internal::_doValidationLastValOfJobTypeDetails == value)
           return model_internal::_doValidationCacheOfJobTypeDetails ;

        _model.model_internal::_jobTypeDetailsIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isJobTypeDetailsAvailable && _internal_jobTypeDetails == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "jobTypeDetails is required"));
        }

        model_internal::_doValidationCacheOfJobTypeDetails = validationFailures;
        model_internal::_doValidationLastValOfJobTypeDetails = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfJobLogFile : Array = null;
    model_internal var _doValidationLastValOfJobLogFile : String;

    model_internal function _doValidationForJobLogFile(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfJobLogFile != null && model_internal::_doValidationLastValOfJobLogFile == value)
           return model_internal::_doValidationCacheOfJobLogFile ;

        _model.model_internal::_jobLogFileIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isJobLogFileAvailable && _internal_jobLogFile == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "jobLogFile is required"));
        }

        model_internal::_doValidationCacheOfJobLogFile = validationFailures;
        model_internal::_doValidationLastValOfJobLogFile = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfJobLogPath : Array = null;
    model_internal var _doValidationLastValOfJobLogPath : String;

    model_internal function _doValidationForJobLogPath(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfJobLogPath != null && model_internal::_doValidationLastValOfJobLogPath == value)
           return model_internal::_doValidationCacheOfJobLogPath ;

        _model.model_internal::_jobLogPathIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isJobLogPathAvailable && _internal_jobLogPath == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "jobLogPath is required"));
        }

        model_internal::_doValidationCacheOfJobLogPath = validationFailures;
        model_internal::_doValidationLastValOfJobLogPath = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfOSystem : Array = null;
    model_internal var _doValidationLastValOfOSystem : String;

    model_internal function _doValidationForOSystem(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfOSystem != null && model_internal::_doValidationLastValOfOSystem == value)
           return model_internal::_doValidationCacheOfOSystem ;

        _model.model_internal::_oSystemIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isOSystemAvailable && _internal_oSystem == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "oSystem is required"));
        }

        model_internal::_doValidationCacheOfOSystem = validationFailures;
        model_internal::_doValidationLastValOfOSystem = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfJobPriority : Array = null;
    model_internal var _doValidationLastValOfJobPriority : String;

    model_internal function _doValidationForJobPriority(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfJobPriority != null && model_internal::_doValidationLastValOfJobPriority == value)
           return model_internal::_doValidationCacheOfJobPriority ;

        _model.model_internal::_jobPriorityIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isJobPriorityAvailable && _internal_jobPriority == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "jobPriority is required"));
        }

        model_internal::_doValidationCacheOfJobPriority = validationFailures;
        model_internal::_doValidationLastValOfJobPriority = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfJsIsActive : Array = null;
    model_internal var _doValidationLastValOfJsIsActive : String;

    model_internal function _doValidationForJsIsActive(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfJsIsActive != null && model_internal::_doValidationLastValOfJsIsActive == value)
           return model_internal::_doValidationCacheOfJsIsActive ;

        _model.model_internal::_jsIsActiveIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isJsIsActiveAvailable && _internal_jsIsActive == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "jsIsActive is required"));
        }

        model_internal::_doValidationCacheOfJsIsActive = validationFailures;
        model_internal::_doValidationLastValOfJsIsActive = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfUserId : Array = null;
    model_internal var _doValidationLastValOfUserId : String;

    model_internal function _doValidationForUserId(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfUserId != null && model_internal::_doValidationLastValOfUserId == value)
           return model_internal::_doValidationCacheOfUserId ;

        _model.model_internal::_userIdIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isUserIdAvailable && _internal_userId == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "userId is required"));
        }

        model_internal::_doValidationCacheOfUserId = validationFailures;
        model_internal::_doValidationLastValOfUserId = value;

        return validationFailures;
    }
    

}

}
