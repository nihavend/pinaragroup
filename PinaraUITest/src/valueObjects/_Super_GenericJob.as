/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this value object you may modify the generated sub-class of this class - GenericJob.as.
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
import valueObjects.BaseJobInfos;
import valueObjects.DependencyList_type;
import valueObjects.Management;
import valueObjects.ScheduleInfo;
import valueObjects.StateInfos;

import flash.net.registerClassAlias;
import flash.net.getClassByAlias;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;

use namespace model_internal;

[ExcludeClass]
public class _Super_GenericJob extends flash.events.EventDispatcher implements com.adobe.fiber.valueobjects.IValueObject
{
    model_internal static function initRemoteClassAliasSingle(cz:Class) : void
    {
    }

    model_internal static function initRemoteClassAliasAllRelated() : void
    {
        valueObjects.BaseJobInfos.initRemoteClassAliasSingleChild();
        valueObjects.JobTypeDetails.initRemoteClassAliasSingleChild();
        valueObjects.DependencyList_type.initRemoteClassAliasSingleChild();
        valueObjects.Item_type.initRemoteClassAliasSingleChild();
        valueObjects.JsDependencyRule.initRemoteClassAliasSingleChild();
        valueObjects.StateInfos.initRemoteClassAliasSingleChild();
        valueObjects.LiveStateInfos_type.initRemoteClassAliasSingleChild();
        valueObjects.LiveStateInfo_type.initRemoteClassAliasSingleChild();
        valueObjects.Management.initRemoteClassAliasSingleChild();
        valueObjects.PeriodInfo.initRemoteClassAliasSingleChild();
        valueObjects.TimeManagement.initRemoteClassAliasSingleChild();
        valueObjects.BornedPlannedTime.initRemoteClassAliasSingleChild();
        valueObjects.JsPlannedTime.initRemoteClassAliasSingleChild();
        valueObjects.JsRealTime.initRemoteClassAliasSingleChild();
        valueObjects.JsTimeOut.initRemoteClassAliasSingleChild();
        valueObjects.CascadingConditions.initRemoteClassAliasSingleChild();
        valueObjects.JobAutoRetryInfo.initRemoteClassAliasSingleChild();
        valueObjects.ScheduleInfo.initRemoteClassAliasSingleChild();
        valueObjects.DaysOfMonth.initRemoteClassAliasSingleChild();
    }

    model_internal var _dminternal_model : _GenericJobEntityMetadata;
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
    private var _internal_type : String;
    private var _internal_groupId : String;
    private var _internal_dangerZoneGroupId : String;
    private var _internal_handlerURI : String;
    private var _internal_Id : String;
    private var _internal_agentId : String;
    private var _internal_baseJobInfos : valueObjects.BaseJobInfos;
    private var _internal_DependencyList : valueObjects.DependencyList_type;
    private var _internal_stateInfos : valueObjects.StateInfos;
    private var _internal_management : valueObjects.Management;
    private var _internal_scheduleInfo : valueObjects.ScheduleInfo;

    private static var emptyArray:Array = new Array();


    /**
     * derived property cache initialization
     */
    model_internal var _cacheInitialized_isValid:Boolean = false;

    model_internal var _changeWatcherArray:Array = new Array();

    public function _Super_GenericJob()
    {
        _model = new _GenericJobEntityMetadata(this);

        // Bind to own data or source properties for cache invalidation triggering
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "type", model_internal::setterListenerType));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "groupId", model_internal::setterListenerGroupId));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "dangerZoneGroupId", model_internal::setterListenerDangerZoneGroupId));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "handlerURI", model_internal::setterListenerHandlerURI));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "Id", model_internal::setterListenerId));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "agentId", model_internal::setterListenerAgentId));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "baseJobInfos", model_internal::setterListenerBaseJobInfos));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "DependencyList", model_internal::setterListenerDependencyList));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "stateInfos", model_internal::setterListenerStateInfos));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "management", model_internal::setterListenerManagement));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "scheduleInfo", model_internal::setterListenerScheduleInfo));

    }

    /**
     * data/source property getters
     */

    [Bindable(event="propertyChange")]
    public function get type() : String
    {
        return _internal_type;
    }

    [Bindable(event="propertyChange")]
    public function get groupId() : String
    {
        return _internal_groupId;
    }

    [Bindable(event="propertyChange")]
    public function get dangerZoneGroupId() : String
    {
        return _internal_dangerZoneGroupId;
    }

    [Bindable(event="propertyChange")]
    public function get handlerURI() : String
    {
        return _internal_handlerURI;
    }

    [Bindable(event="propertyChange")]
    public function get Id() : String
    {
        return _internal_Id;
    }

    [Bindable(event="propertyChange")]
    public function get agentId() : String
    {
        return _internal_agentId;
    }

    [Bindable(event="propertyChange")]
    public function get baseJobInfos() : valueObjects.BaseJobInfos
    {
        return _internal_baseJobInfos;
    }

    [Bindable(event="propertyChange")]
    public function get DependencyList() : valueObjects.DependencyList_type
    {
        return _internal_DependencyList;
    }

    [Bindable(event="propertyChange")]
    public function get stateInfos() : valueObjects.StateInfos
    {
        return _internal_stateInfos;
    }

    [Bindable(event="propertyChange")]
    public function get management() : valueObjects.Management
    {
        return _internal_management;
    }

    [Bindable(event="propertyChange")]
    public function get scheduleInfo() : valueObjects.ScheduleInfo
    {
        return _internal_scheduleInfo;
    }

    public function clearAssociations() : void
    {
    }

    /**
     * data/source property setters
     */

    public function set type(value:String) : void
    {
        var oldValue:String = _internal_type;
        if (oldValue !== value)
        {
            _internal_type = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "type", oldValue, _internal_type));
        }
    }

    public function set groupId(value:String) : void
    {
        var oldValue:String = _internal_groupId;
        if (oldValue !== value)
        {
            _internal_groupId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "groupId", oldValue, _internal_groupId));
        }
    }

    public function set dangerZoneGroupId(value:String) : void
    {
        var oldValue:String = _internal_dangerZoneGroupId;
        if (oldValue !== value)
        {
            _internal_dangerZoneGroupId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "dangerZoneGroupId", oldValue, _internal_dangerZoneGroupId));
        }
    }

    public function set handlerURI(value:String) : void
    {
        var oldValue:String = _internal_handlerURI;
        if (oldValue !== value)
        {
            _internal_handlerURI = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "handlerURI", oldValue, _internal_handlerURI));
        }
    }

    public function set Id(value:String) : void
    {
        var oldValue:String = _internal_Id;
        if (oldValue !== value)
        {
            _internal_Id = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "Id", oldValue, _internal_Id));
        }
    }

    public function set agentId(value:String) : void
    {
        var oldValue:String = _internal_agentId;
        if (oldValue !== value)
        {
            _internal_agentId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "agentId", oldValue, _internal_agentId));
        }
    }

    public function set baseJobInfos(value:valueObjects.BaseJobInfos) : void
    {
        var oldValue:valueObjects.BaseJobInfos = _internal_baseJobInfos;
        if (oldValue !== value)
        {
            _internal_baseJobInfos = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "baseJobInfos", oldValue, _internal_baseJobInfos));
        }
    }

    public function set DependencyList(value:valueObjects.DependencyList_type) : void
    {
        var oldValue:valueObjects.DependencyList_type = _internal_DependencyList;
        if (oldValue !== value)
        {
            _internal_DependencyList = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "DependencyList", oldValue, _internal_DependencyList));
        }
    }

    public function set stateInfos(value:valueObjects.StateInfos) : void
    {
        var oldValue:valueObjects.StateInfos = _internal_stateInfos;
        if (oldValue !== value)
        {
            _internal_stateInfos = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "stateInfos", oldValue, _internal_stateInfos));
        }
    }

    public function set management(value:valueObjects.Management) : void
    {
        var oldValue:valueObjects.Management = _internal_management;
        if (oldValue !== value)
        {
            _internal_management = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "management", oldValue, _internal_management));
        }
    }

    public function set scheduleInfo(value:valueObjects.ScheduleInfo) : void
    {
        var oldValue:valueObjects.ScheduleInfo = _internal_scheduleInfo;
        if (oldValue !== value)
        {
            _internal_scheduleInfo = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "scheduleInfo", oldValue, _internal_scheduleInfo));
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

    model_internal function setterListenerType(value:flash.events.Event):void
    {
        _model.invalidateDependentOnType();
    }

    model_internal function setterListenerGroupId(value:flash.events.Event):void
    {
        _model.invalidateDependentOnGroupId();
    }

    model_internal function setterListenerDangerZoneGroupId(value:flash.events.Event):void
    {
        _model.invalidateDependentOnDangerZoneGroupId();
    }

    model_internal function setterListenerHandlerURI(value:flash.events.Event):void
    {
        _model.invalidateDependentOnHandlerURI();
    }

    model_internal function setterListenerId(value:flash.events.Event):void
    {
        _model.invalidateDependentOnId();
    }

    model_internal function setterListenerAgentId(value:flash.events.Event):void
    {
        _model.invalidateDependentOnAgentId();
    }

    model_internal function setterListenerBaseJobInfos(value:flash.events.Event):void
    {
        _model.invalidateDependentOnBaseJobInfos();
    }

    model_internal function setterListenerDependencyList(value:flash.events.Event):void
    {
        _model.invalidateDependentOnDependencyList();
    }

    model_internal function setterListenerStateInfos(value:flash.events.Event):void
    {
        _model.invalidateDependentOnStateInfos();
    }

    model_internal function setterListenerManagement(value:flash.events.Event):void
    {
        _model.invalidateDependentOnManagement();
    }

    model_internal function setterListenerScheduleInfo(value:flash.events.Event):void
    {
        _model.invalidateDependentOnScheduleInfo();
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
        if (!_model.typeIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_typeValidationFailureMessages);
        }
        if (!_model.groupIdIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_groupIdValidationFailureMessages);
        }
        if (!_model.dangerZoneGroupIdIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_dangerZoneGroupIdValidationFailureMessages);
        }
        if (!_model.handlerURIIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_handlerURIValidationFailureMessages);
        }
        if (!_model.IdIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_IdValidationFailureMessages);
        }
        if (!_model.agentIdIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_agentIdValidationFailureMessages);
        }
        if (!_model.baseJobInfosIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_baseJobInfosValidationFailureMessages);
        }
        if (!_model.DependencyListIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_DependencyListValidationFailureMessages);
        }
        if (!_model.stateInfosIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_stateInfosValidationFailureMessages);
        }
        if (!_model.managementIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_managementValidationFailureMessages);
        }
        if (!_model.scheduleInfoIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_scheduleInfoValidationFailureMessages);
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
    public function get _model() : _GenericJobEntityMetadata
    {
        return model_internal::_dminternal_model;
    }

    public function set _model(value : _GenericJobEntityMetadata) : void
    {
        var oldValue : _GenericJobEntityMetadata = model_internal::_dminternal_model;
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

    model_internal var _doValidationCacheOfType : Array = null;
    model_internal var _doValidationLastValOfType : String;

    model_internal function _doValidationForType(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfType != null && model_internal::_doValidationLastValOfType == value)
           return model_internal::_doValidationCacheOfType ;

        _model.model_internal::_typeIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isTypeAvailable && _internal_type == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "type is required"));
        }

        model_internal::_doValidationCacheOfType = validationFailures;
        model_internal::_doValidationLastValOfType = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfGroupId : Array = null;
    model_internal var _doValidationLastValOfGroupId : String;

    model_internal function _doValidationForGroupId(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfGroupId != null && model_internal::_doValidationLastValOfGroupId == value)
           return model_internal::_doValidationCacheOfGroupId ;

        _model.model_internal::_groupIdIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isGroupIdAvailable && _internal_groupId == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "groupId is required"));
        }

        model_internal::_doValidationCacheOfGroupId = validationFailures;
        model_internal::_doValidationLastValOfGroupId = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfDangerZoneGroupId : Array = null;
    model_internal var _doValidationLastValOfDangerZoneGroupId : String;

    model_internal function _doValidationForDangerZoneGroupId(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfDangerZoneGroupId != null && model_internal::_doValidationLastValOfDangerZoneGroupId == value)
           return model_internal::_doValidationCacheOfDangerZoneGroupId ;

        _model.model_internal::_dangerZoneGroupIdIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isDangerZoneGroupIdAvailable && _internal_dangerZoneGroupId == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "dangerZoneGroupId is required"));
        }

        model_internal::_doValidationCacheOfDangerZoneGroupId = validationFailures;
        model_internal::_doValidationLastValOfDangerZoneGroupId = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfHandlerURI : Array = null;
    model_internal var _doValidationLastValOfHandlerURI : String;

    model_internal function _doValidationForHandlerURI(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfHandlerURI != null && model_internal::_doValidationLastValOfHandlerURI == value)
           return model_internal::_doValidationCacheOfHandlerURI ;

        _model.model_internal::_handlerURIIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isHandlerURIAvailable && _internal_handlerURI == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "handlerURI is required"));
        }

        model_internal::_doValidationCacheOfHandlerURI = validationFailures;
        model_internal::_doValidationLastValOfHandlerURI = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfId : Array = null;
    model_internal var _doValidationLastValOfId : String;

    model_internal function _doValidationForId(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfId != null && model_internal::_doValidationLastValOfId == value)
           return model_internal::_doValidationCacheOfId ;

        _model.model_internal::_IdIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isIdAvailable && _internal_Id == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "Id is required"));
        }

        model_internal::_doValidationCacheOfId = validationFailures;
        model_internal::_doValidationLastValOfId = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfAgentId : Array = null;
    model_internal var _doValidationLastValOfAgentId : String;

    model_internal function _doValidationForAgentId(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfAgentId != null && model_internal::_doValidationLastValOfAgentId == value)
           return model_internal::_doValidationCacheOfAgentId ;

        _model.model_internal::_agentIdIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isAgentIdAvailable && _internal_agentId == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "agentId is required"));
        }

        model_internal::_doValidationCacheOfAgentId = validationFailures;
        model_internal::_doValidationLastValOfAgentId = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfBaseJobInfos : Array = null;
    model_internal var _doValidationLastValOfBaseJobInfos : valueObjects.BaseJobInfos;

    model_internal function _doValidationForBaseJobInfos(valueIn:Object):Array
    {
        var value : valueObjects.BaseJobInfos = valueIn as valueObjects.BaseJobInfos;

        if (model_internal::_doValidationCacheOfBaseJobInfos != null && model_internal::_doValidationLastValOfBaseJobInfos == value)
           return model_internal::_doValidationCacheOfBaseJobInfos ;

        _model.model_internal::_baseJobInfosIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isBaseJobInfosAvailable && _internal_baseJobInfos == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "baseJobInfos is required"));
        }

        model_internal::_doValidationCacheOfBaseJobInfos = validationFailures;
        model_internal::_doValidationLastValOfBaseJobInfos = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfDependencyList : Array = null;
    model_internal var _doValidationLastValOfDependencyList : valueObjects.DependencyList_type;

    model_internal function _doValidationForDependencyList(valueIn:Object):Array
    {
        var value : valueObjects.DependencyList_type = valueIn as valueObjects.DependencyList_type;

        if (model_internal::_doValidationCacheOfDependencyList != null && model_internal::_doValidationLastValOfDependencyList == value)
           return model_internal::_doValidationCacheOfDependencyList ;

        _model.model_internal::_DependencyListIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isDependencyListAvailable && _internal_DependencyList == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "DependencyList is required"));
        }

        model_internal::_doValidationCacheOfDependencyList = validationFailures;
        model_internal::_doValidationLastValOfDependencyList = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfStateInfos : Array = null;
    model_internal var _doValidationLastValOfStateInfos : valueObjects.StateInfos;

    model_internal function _doValidationForStateInfos(valueIn:Object):Array
    {
        var value : valueObjects.StateInfos = valueIn as valueObjects.StateInfos;

        if (model_internal::_doValidationCacheOfStateInfos != null && model_internal::_doValidationLastValOfStateInfos == value)
           return model_internal::_doValidationCacheOfStateInfos ;

        _model.model_internal::_stateInfosIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isStateInfosAvailable && _internal_stateInfos == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "stateInfos is required"));
        }

        model_internal::_doValidationCacheOfStateInfos = validationFailures;
        model_internal::_doValidationLastValOfStateInfos = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfManagement : Array = null;
    model_internal var _doValidationLastValOfManagement : valueObjects.Management;

    model_internal function _doValidationForManagement(valueIn:Object):Array
    {
        var value : valueObjects.Management = valueIn as valueObjects.Management;

        if (model_internal::_doValidationCacheOfManagement != null && model_internal::_doValidationLastValOfManagement == value)
           return model_internal::_doValidationCacheOfManagement ;

        _model.model_internal::_managementIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isManagementAvailable && _internal_management == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "management is required"));
        }

        model_internal::_doValidationCacheOfManagement = validationFailures;
        model_internal::_doValidationLastValOfManagement = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfScheduleInfo : Array = null;
    model_internal var _doValidationLastValOfScheduleInfo : valueObjects.ScheduleInfo;

    model_internal function _doValidationForScheduleInfo(valueIn:Object):Array
    {
        var value : valueObjects.ScheduleInfo = valueIn as valueObjects.ScheduleInfo;

        if (model_internal::_doValidationCacheOfScheduleInfo != null && model_internal::_doValidationLastValOfScheduleInfo == value)
           return model_internal::_doValidationCacheOfScheduleInfo ;

        _model.model_internal::_scheduleInfoIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isScheduleInfoAvailable && _internal_scheduleInfo == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "scheduleInfo is required"));
        }

        model_internal::_doValidationCacheOfScheduleInfo = validationFailures;
        model_internal::_doValidationLastValOfScheduleInfo = value;

        return validationFailures;
    }
    

}

}
