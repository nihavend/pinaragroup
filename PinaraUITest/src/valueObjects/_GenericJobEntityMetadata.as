
/**
 * This is a generated class and is not intended for modification.  
 */
package valueObjects
{
import com.adobe.fiber.styles.IStyle;
import com.adobe.fiber.styles.Style;
import com.adobe.fiber.styles.StyleValidator;
import com.adobe.fiber.valueobjects.AbstractEntityMetadata;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import mx.events.ValidationResultEvent;
import valueObjects.BaseJobInfos;
import valueObjects.DependencyList_type;
import valueObjects.Management;
import valueObjects.ScheduleInfo;
import valueObjects.StateInfos;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IModelType;
import mx.events.PropertyChangeEvent;

use namespace model_internal;

[ExcludeClass]
internal class _GenericJobEntityMetadata extends com.adobe.fiber.valueobjects.AbstractEntityMetadata
{
    private static var emptyArray:Array = new Array();

    model_internal static var allProperties:Array = new Array("type", "groupId", "dangerZoneGroupId", "handlerURI", "Id", "agentId", "baseJobInfos", "DependencyList", "stateInfos", "management", "scheduleInfo");
    model_internal static var allAssociationProperties:Array = new Array();
    model_internal static var allRequiredProperties:Array = new Array("type", "groupId", "dangerZoneGroupId", "handlerURI", "Id", "agentId", "baseJobInfos", "DependencyList", "stateInfos", "management", "scheduleInfo");
    model_internal static var allAlwaysAvailableProperties:Array = new Array("type", "groupId", "dangerZoneGroupId", "handlerURI", "Id", "agentId", "baseJobInfos", "DependencyList", "stateInfos", "management", "scheduleInfo");
    model_internal static var guardedProperties:Array = new Array();
    model_internal static var dataProperties:Array = new Array("type", "groupId", "dangerZoneGroupId", "handlerURI", "Id", "agentId", "baseJobInfos", "DependencyList", "stateInfos", "management", "scheduleInfo");
    model_internal static var sourceProperties:Array = emptyArray
    model_internal static var nonDerivedProperties:Array = new Array("type", "groupId", "dangerZoneGroupId", "handlerURI", "Id", "agentId", "baseJobInfos", "DependencyList", "stateInfos", "management", "scheduleInfo");
    model_internal static var derivedProperties:Array = new Array();
    model_internal static var collectionProperties:Array = new Array();
    model_internal static var collectionBaseMap:Object;
    model_internal static var entityName:String = "GenericJob";
    model_internal static var dependentsOnMap:Object;
    model_internal static var dependedOnServices:Array = new Array();
    model_internal static var propertyTypeMap:Object;

    
    model_internal var _typeIsValid:Boolean;
    model_internal var _typeValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _typeIsValidCacheInitialized:Boolean = false;
    model_internal var _typeValidationFailureMessages:Array;
    
    model_internal var _groupIdIsValid:Boolean;
    model_internal var _groupIdValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _groupIdIsValidCacheInitialized:Boolean = false;
    model_internal var _groupIdValidationFailureMessages:Array;
    
    model_internal var _dangerZoneGroupIdIsValid:Boolean;
    model_internal var _dangerZoneGroupIdValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _dangerZoneGroupIdIsValidCacheInitialized:Boolean = false;
    model_internal var _dangerZoneGroupIdValidationFailureMessages:Array;
    
    model_internal var _handlerURIIsValid:Boolean;
    model_internal var _handlerURIValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _handlerURIIsValidCacheInitialized:Boolean = false;
    model_internal var _handlerURIValidationFailureMessages:Array;
    
    model_internal var _IdIsValid:Boolean;
    model_internal var _IdValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _IdIsValidCacheInitialized:Boolean = false;
    model_internal var _IdValidationFailureMessages:Array;
    
    model_internal var _agentIdIsValid:Boolean;
    model_internal var _agentIdValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _agentIdIsValidCacheInitialized:Boolean = false;
    model_internal var _agentIdValidationFailureMessages:Array;
    
    model_internal var _baseJobInfosIsValid:Boolean;
    model_internal var _baseJobInfosValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _baseJobInfosIsValidCacheInitialized:Boolean = false;
    model_internal var _baseJobInfosValidationFailureMessages:Array;
    
    model_internal var _DependencyListIsValid:Boolean;
    model_internal var _DependencyListValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _DependencyListIsValidCacheInitialized:Boolean = false;
    model_internal var _DependencyListValidationFailureMessages:Array;
    
    model_internal var _stateInfosIsValid:Boolean;
    model_internal var _stateInfosValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _stateInfosIsValidCacheInitialized:Boolean = false;
    model_internal var _stateInfosValidationFailureMessages:Array;
    
    model_internal var _managementIsValid:Boolean;
    model_internal var _managementValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _managementIsValidCacheInitialized:Boolean = false;
    model_internal var _managementValidationFailureMessages:Array;
    
    model_internal var _scheduleInfoIsValid:Boolean;
    model_internal var _scheduleInfoValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _scheduleInfoIsValidCacheInitialized:Boolean = false;
    model_internal var _scheduleInfoValidationFailureMessages:Array;

    model_internal var _instance:_Super_GenericJob;
    model_internal static var _nullStyle:com.adobe.fiber.styles.Style = new com.adobe.fiber.styles.Style();

    public function _GenericJobEntityMetadata(value : _Super_GenericJob)
    {
        // initialize property maps
        if (model_internal::dependentsOnMap == null)
        {
            // dependents map
            model_internal::dependentsOnMap = new Object();
            model_internal::dependentsOnMap["type"] = new Array();
            model_internal::dependentsOnMap["groupId"] = new Array();
            model_internal::dependentsOnMap["dangerZoneGroupId"] = new Array();
            model_internal::dependentsOnMap["handlerURI"] = new Array();
            model_internal::dependentsOnMap["Id"] = new Array();
            model_internal::dependentsOnMap["agentId"] = new Array();
            model_internal::dependentsOnMap["baseJobInfos"] = new Array();
            model_internal::dependentsOnMap["DependencyList"] = new Array();
            model_internal::dependentsOnMap["stateInfos"] = new Array();
            model_internal::dependentsOnMap["management"] = new Array();
            model_internal::dependentsOnMap["scheduleInfo"] = new Array();

            // collection base map
            model_internal::collectionBaseMap = new Object();
        }

        // Property type Map
        model_internal::propertyTypeMap = new Object();
        model_internal::propertyTypeMap["type"] = "String";
        model_internal::propertyTypeMap["groupId"] = "String";
        model_internal::propertyTypeMap["dangerZoneGroupId"] = "String";
        model_internal::propertyTypeMap["handlerURI"] = "String";
        model_internal::propertyTypeMap["Id"] = "String";
        model_internal::propertyTypeMap["agentId"] = "String";
        model_internal::propertyTypeMap["baseJobInfos"] = "valueObjects.BaseJobInfos";
        model_internal::propertyTypeMap["DependencyList"] = "valueObjects.DependencyList_type";
        model_internal::propertyTypeMap["stateInfos"] = "valueObjects.StateInfos";
        model_internal::propertyTypeMap["management"] = "valueObjects.Management";
        model_internal::propertyTypeMap["scheduleInfo"] = "valueObjects.ScheduleInfo";

        model_internal::_instance = value;
        model_internal::_typeValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForType);
        model_internal::_typeValidator.required = true;
        model_internal::_typeValidator.requiredFieldError = "type is required";
        //model_internal::_typeValidator.source = model_internal::_instance;
        //model_internal::_typeValidator.property = "type";
        model_internal::_groupIdValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForGroupId);
        model_internal::_groupIdValidator.required = true;
        model_internal::_groupIdValidator.requiredFieldError = "groupId is required";
        //model_internal::_groupIdValidator.source = model_internal::_instance;
        //model_internal::_groupIdValidator.property = "groupId";
        model_internal::_dangerZoneGroupIdValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForDangerZoneGroupId);
        model_internal::_dangerZoneGroupIdValidator.required = true;
        model_internal::_dangerZoneGroupIdValidator.requiredFieldError = "dangerZoneGroupId is required";
        //model_internal::_dangerZoneGroupIdValidator.source = model_internal::_instance;
        //model_internal::_dangerZoneGroupIdValidator.property = "dangerZoneGroupId";
        model_internal::_handlerURIValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForHandlerURI);
        model_internal::_handlerURIValidator.required = true;
        model_internal::_handlerURIValidator.requiredFieldError = "handlerURI is required";
        //model_internal::_handlerURIValidator.source = model_internal::_instance;
        //model_internal::_handlerURIValidator.property = "handlerURI";
        model_internal::_IdValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForId);
        model_internal::_IdValidator.required = true;
        model_internal::_IdValidator.requiredFieldError = "Id is required";
        //model_internal::_IdValidator.source = model_internal::_instance;
        //model_internal::_IdValidator.property = "Id";
        model_internal::_agentIdValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForAgentId);
        model_internal::_agentIdValidator.required = true;
        model_internal::_agentIdValidator.requiredFieldError = "agentId is required";
        //model_internal::_agentIdValidator.source = model_internal::_instance;
        //model_internal::_agentIdValidator.property = "agentId";
        model_internal::_baseJobInfosValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForBaseJobInfos);
        model_internal::_baseJobInfosValidator.required = true;
        model_internal::_baseJobInfosValidator.requiredFieldError = "baseJobInfos is required";
        //model_internal::_baseJobInfosValidator.source = model_internal::_instance;
        //model_internal::_baseJobInfosValidator.property = "baseJobInfos";
        model_internal::_DependencyListValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForDependencyList);
        model_internal::_DependencyListValidator.required = true;
        model_internal::_DependencyListValidator.requiredFieldError = "DependencyList is required";
        //model_internal::_DependencyListValidator.source = model_internal::_instance;
        //model_internal::_DependencyListValidator.property = "DependencyList";
        model_internal::_stateInfosValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForStateInfos);
        model_internal::_stateInfosValidator.required = true;
        model_internal::_stateInfosValidator.requiredFieldError = "stateInfos is required";
        //model_internal::_stateInfosValidator.source = model_internal::_instance;
        //model_internal::_stateInfosValidator.property = "stateInfos";
        model_internal::_managementValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForManagement);
        model_internal::_managementValidator.required = true;
        model_internal::_managementValidator.requiredFieldError = "management is required";
        //model_internal::_managementValidator.source = model_internal::_instance;
        //model_internal::_managementValidator.property = "management";
        model_internal::_scheduleInfoValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForScheduleInfo);
        model_internal::_scheduleInfoValidator.required = true;
        model_internal::_scheduleInfoValidator.requiredFieldError = "scheduleInfo is required";
        //model_internal::_scheduleInfoValidator.source = model_internal::_instance;
        //model_internal::_scheduleInfoValidator.property = "scheduleInfo";
    }

    override public function getEntityName():String
    {
        return model_internal::entityName;
    }

    override public function getProperties():Array
    {
        return model_internal::allProperties;
    }

    override public function getAssociationProperties():Array
    {
        return model_internal::allAssociationProperties;
    }

    override public function getRequiredProperties():Array
    {
         return model_internal::allRequiredProperties;   
    }

    override public function getDataProperties():Array
    {
        return model_internal::dataProperties;
    }

    public function getSourceProperties():Array
    {
        return model_internal::sourceProperties;
    }

    public function getNonDerivedProperties():Array
    {
        return model_internal::nonDerivedProperties;
    }

    override public function getGuardedProperties():Array
    {
        return model_internal::guardedProperties;
    }

    override public function getUnguardedProperties():Array
    {
        return model_internal::allAlwaysAvailableProperties;
    }

    override public function getDependants(propertyName:String):Array
    {
       if (model_internal::nonDerivedProperties.indexOf(propertyName) == -1)
            throw new Error(propertyName + " is not a data property of entity GenericJob");
            
       return model_internal::dependentsOnMap[propertyName] as Array;  
    }

    override public function getDependedOnServices():Array
    {
        return model_internal::dependedOnServices;
    }

    override public function getCollectionProperties():Array
    {
        return model_internal::collectionProperties;
    }

    override public function getCollectionBase(propertyName:String):String
    {
        if (model_internal::collectionProperties.indexOf(propertyName) == -1)
            throw new Error(propertyName + " is not a collection property of entity GenericJob");

        return model_internal::collectionBaseMap[propertyName];
    }
    
    override public function getPropertyType(propertyName:String):String
    {
        if (model_internal::allProperties.indexOf(propertyName) == -1)
            throw new Error(propertyName + " is not a property of GenericJob");

        return model_internal::propertyTypeMap[propertyName];
    }

    override public function getAvailableProperties():com.adobe.fiber.valueobjects.IPropertyIterator
    {
        return new com.adobe.fiber.valueobjects.AvailablePropertyIterator(this);
    }

    override public function getValue(propertyName:String):*
    {
        if (model_internal::allProperties.indexOf(propertyName) == -1)
        {
            throw new Error(propertyName + " does not exist for entity GenericJob");
        }

        return model_internal::_instance[propertyName];
    }

    override public function setValue(propertyName:String, value:*):void
    {
        if (model_internal::nonDerivedProperties.indexOf(propertyName) == -1)
        {
            throw new Error(propertyName + " is not a modifiable property of entity GenericJob");
        }

        model_internal::_instance[propertyName] = value;
    }

    override public function getMappedByProperty(associationProperty:String):String
    {
        switch(associationProperty)
        {
            default:
            {
                return null;
            }
        }
    }

    override public function getPropertyLength(propertyName:String):int
    {
        switch(propertyName)
        {
            default:
            {
                return 0;
            }
        }
    }

    override public function isAvailable(propertyName:String):Boolean
    {
        if (model_internal::allProperties.indexOf(propertyName) == -1)
        {
            throw new Error(propertyName + " does not exist for entity GenericJob");
        }

        if (model_internal::allAlwaysAvailableProperties.indexOf(propertyName) != -1)
        {
            return true;
        }

        switch(propertyName)
        {
            default:
            {
                return true;
            }
        }
    }

    override public function getIdentityMap():Object
    {
        var returnMap:Object = new Object();

        return returnMap;
    }

    [Bindable(event="propertyChange")]
    override public function get invalidConstraints():Array
    {
        if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
        {
            return model_internal::_instance.model_internal::_invalidConstraints;
        }
        else
        {
            // recalculate isValid
            model_internal::_instance.model_internal::_isValid = model_internal::_instance.model_internal::calculateIsValid();
            return model_internal::_instance.model_internal::_invalidConstraints;        
        }
    }

    [Bindable(event="propertyChange")]
    override public function get validationFailureMessages():Array
    {
        if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
        {
            return model_internal::_instance.model_internal::_validationFailureMessages;
        }
        else
        {
            // recalculate isValid
            model_internal::_instance.model_internal::_isValid = model_internal::_instance.model_internal::calculateIsValid();
            return model_internal::_instance.model_internal::_validationFailureMessages;
        }
    }

    override public function getDependantInvalidConstraints(propertyName:String):Array
    {
        var dependants:Array = getDependants(propertyName);
        if (dependants.length == 0)
        {
            return emptyArray;
        }

        var currentlyInvalid:Array = invalidConstraints;
        if (currentlyInvalid.length == 0)
        {
            return emptyArray;
        }

        var filterFunc:Function = function(element:*, index:int, arr:Array):Boolean
        {
            return dependants.indexOf(element) > -1;
        }

        return currentlyInvalid.filter(filterFunc);
    }

    /**
     * isValid
     */
    [Bindable(event="propertyChange")] 
    public function get isValid() : Boolean
    {
        if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
        {
            return model_internal::_instance.model_internal::_isValid;
        }
        else
        {
            // recalculate isValid
            model_internal::_instance.model_internal::_isValid = model_internal::_instance.model_internal::calculateIsValid();
            return model_internal::_instance.model_internal::_isValid;
        }
    }

    [Bindable(event="propertyChange")]
    public function get isTypeAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isGroupIdAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isDangerZoneGroupIdAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isHandlerURIAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isIdAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isAgentIdAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isBaseJobInfosAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isDependencyListAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isStateInfosAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isManagementAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isScheduleInfoAvailable():Boolean
    {
        return true;
    }


    /**
     * derived property recalculation
     */
    public function invalidateDependentOnType():void
    {
        if (model_internal::_typeIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfType = null;
            model_internal::calculateTypeIsValid();
        }
    }
    public function invalidateDependentOnGroupId():void
    {
        if (model_internal::_groupIdIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfGroupId = null;
            model_internal::calculateGroupIdIsValid();
        }
    }
    public function invalidateDependentOnDangerZoneGroupId():void
    {
        if (model_internal::_dangerZoneGroupIdIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfDangerZoneGroupId = null;
            model_internal::calculateDangerZoneGroupIdIsValid();
        }
    }
    public function invalidateDependentOnHandlerURI():void
    {
        if (model_internal::_handlerURIIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfHandlerURI = null;
            model_internal::calculateHandlerURIIsValid();
        }
    }
    public function invalidateDependentOnId():void
    {
        if (model_internal::_IdIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfId = null;
            model_internal::calculateIdIsValid();
        }
    }
    public function invalidateDependentOnAgentId():void
    {
        if (model_internal::_agentIdIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfAgentId = null;
            model_internal::calculateAgentIdIsValid();
        }
    }
    public function invalidateDependentOnBaseJobInfos():void
    {
        if (model_internal::_baseJobInfosIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfBaseJobInfos = null;
            model_internal::calculateBaseJobInfosIsValid();
        }
    }
    public function invalidateDependentOnDependencyList():void
    {
        if (model_internal::_DependencyListIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfDependencyList = null;
            model_internal::calculateDependencyListIsValid();
        }
    }
    public function invalidateDependentOnStateInfos():void
    {
        if (model_internal::_stateInfosIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfStateInfos = null;
            model_internal::calculateStateInfosIsValid();
        }
    }
    public function invalidateDependentOnManagement():void
    {
        if (model_internal::_managementIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfManagement = null;
            model_internal::calculateManagementIsValid();
        }
    }
    public function invalidateDependentOnScheduleInfo():void
    {
        if (model_internal::_scheduleInfoIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfScheduleInfo = null;
            model_internal::calculateScheduleInfoIsValid();
        }
    }

    model_internal function fireChangeEvent(propertyName:String, oldValue:Object, newValue:Object):void
    {
        this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, propertyName, oldValue, newValue));
    }

    [Bindable(event="propertyChange")]   
    public function get typeStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get typeValidator() : StyleValidator
    {
        return model_internal::_typeValidator;
    }

    model_internal function set _typeIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_typeIsValid;         
        if (oldValue !== value)
        {
            model_internal::_typeIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "typeIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get typeIsValid():Boolean
    {
        if (!model_internal::_typeIsValidCacheInitialized)
        {
            model_internal::calculateTypeIsValid();
        }

        return model_internal::_typeIsValid;
    }

    model_internal function calculateTypeIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_typeValidator.validate(model_internal::_instance.type)
        model_internal::_typeIsValid_der = (valRes.results == null);
        model_internal::_typeIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::typeValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::typeValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get typeValidationFailureMessages():Array
    {
        if (model_internal::_typeValidationFailureMessages == null)
            model_internal::calculateTypeIsValid();

        return _typeValidationFailureMessages;
    }

    model_internal function set typeValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_typeValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_typeValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "typeValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get groupIdStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get groupIdValidator() : StyleValidator
    {
        return model_internal::_groupIdValidator;
    }

    model_internal function set _groupIdIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_groupIdIsValid;         
        if (oldValue !== value)
        {
            model_internal::_groupIdIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "groupIdIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get groupIdIsValid():Boolean
    {
        if (!model_internal::_groupIdIsValidCacheInitialized)
        {
            model_internal::calculateGroupIdIsValid();
        }

        return model_internal::_groupIdIsValid;
    }

    model_internal function calculateGroupIdIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_groupIdValidator.validate(model_internal::_instance.groupId)
        model_internal::_groupIdIsValid_der = (valRes.results == null);
        model_internal::_groupIdIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::groupIdValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::groupIdValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get groupIdValidationFailureMessages():Array
    {
        if (model_internal::_groupIdValidationFailureMessages == null)
            model_internal::calculateGroupIdIsValid();

        return _groupIdValidationFailureMessages;
    }

    model_internal function set groupIdValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_groupIdValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_groupIdValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "groupIdValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get dangerZoneGroupIdStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get dangerZoneGroupIdValidator() : StyleValidator
    {
        return model_internal::_dangerZoneGroupIdValidator;
    }

    model_internal function set _dangerZoneGroupIdIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_dangerZoneGroupIdIsValid;         
        if (oldValue !== value)
        {
            model_internal::_dangerZoneGroupIdIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "dangerZoneGroupIdIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get dangerZoneGroupIdIsValid():Boolean
    {
        if (!model_internal::_dangerZoneGroupIdIsValidCacheInitialized)
        {
            model_internal::calculateDangerZoneGroupIdIsValid();
        }

        return model_internal::_dangerZoneGroupIdIsValid;
    }

    model_internal function calculateDangerZoneGroupIdIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_dangerZoneGroupIdValidator.validate(model_internal::_instance.dangerZoneGroupId)
        model_internal::_dangerZoneGroupIdIsValid_der = (valRes.results == null);
        model_internal::_dangerZoneGroupIdIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::dangerZoneGroupIdValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::dangerZoneGroupIdValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get dangerZoneGroupIdValidationFailureMessages():Array
    {
        if (model_internal::_dangerZoneGroupIdValidationFailureMessages == null)
            model_internal::calculateDangerZoneGroupIdIsValid();

        return _dangerZoneGroupIdValidationFailureMessages;
    }

    model_internal function set dangerZoneGroupIdValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_dangerZoneGroupIdValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_dangerZoneGroupIdValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "dangerZoneGroupIdValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get handlerURIStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get handlerURIValidator() : StyleValidator
    {
        return model_internal::_handlerURIValidator;
    }

    model_internal function set _handlerURIIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_handlerURIIsValid;         
        if (oldValue !== value)
        {
            model_internal::_handlerURIIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "handlerURIIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get handlerURIIsValid():Boolean
    {
        if (!model_internal::_handlerURIIsValidCacheInitialized)
        {
            model_internal::calculateHandlerURIIsValid();
        }

        return model_internal::_handlerURIIsValid;
    }

    model_internal function calculateHandlerURIIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_handlerURIValidator.validate(model_internal::_instance.handlerURI)
        model_internal::_handlerURIIsValid_der = (valRes.results == null);
        model_internal::_handlerURIIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::handlerURIValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::handlerURIValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get handlerURIValidationFailureMessages():Array
    {
        if (model_internal::_handlerURIValidationFailureMessages == null)
            model_internal::calculateHandlerURIIsValid();

        return _handlerURIValidationFailureMessages;
    }

    model_internal function set handlerURIValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_handlerURIValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_handlerURIValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "handlerURIValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get IdStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get IdValidator() : StyleValidator
    {
        return model_internal::_IdValidator;
    }

    model_internal function set _IdIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_IdIsValid;         
        if (oldValue !== value)
        {
            model_internal::_IdIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "IdIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get IdIsValid():Boolean
    {
        if (!model_internal::_IdIsValidCacheInitialized)
        {
            model_internal::calculateIdIsValid();
        }

        return model_internal::_IdIsValid;
    }

    model_internal function calculateIdIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_IdValidator.validate(model_internal::_instance.Id)
        model_internal::_IdIsValid_der = (valRes.results == null);
        model_internal::_IdIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::IdValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::IdValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get IdValidationFailureMessages():Array
    {
        if (model_internal::_IdValidationFailureMessages == null)
            model_internal::calculateIdIsValid();

        return _IdValidationFailureMessages;
    }

    model_internal function set IdValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_IdValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_IdValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "IdValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get agentIdStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get agentIdValidator() : StyleValidator
    {
        return model_internal::_agentIdValidator;
    }

    model_internal function set _agentIdIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_agentIdIsValid;         
        if (oldValue !== value)
        {
            model_internal::_agentIdIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "agentIdIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get agentIdIsValid():Boolean
    {
        if (!model_internal::_agentIdIsValidCacheInitialized)
        {
            model_internal::calculateAgentIdIsValid();
        }

        return model_internal::_agentIdIsValid;
    }

    model_internal function calculateAgentIdIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_agentIdValidator.validate(model_internal::_instance.agentId)
        model_internal::_agentIdIsValid_der = (valRes.results == null);
        model_internal::_agentIdIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::agentIdValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::agentIdValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get agentIdValidationFailureMessages():Array
    {
        if (model_internal::_agentIdValidationFailureMessages == null)
            model_internal::calculateAgentIdIsValid();

        return _agentIdValidationFailureMessages;
    }

    model_internal function set agentIdValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_agentIdValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_agentIdValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "agentIdValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get baseJobInfosStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get baseJobInfosValidator() : StyleValidator
    {
        return model_internal::_baseJobInfosValidator;
    }

    model_internal function set _baseJobInfosIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_baseJobInfosIsValid;         
        if (oldValue !== value)
        {
            model_internal::_baseJobInfosIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "baseJobInfosIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get baseJobInfosIsValid():Boolean
    {
        if (!model_internal::_baseJobInfosIsValidCacheInitialized)
        {
            model_internal::calculateBaseJobInfosIsValid();
        }

        return model_internal::_baseJobInfosIsValid;
    }

    model_internal function calculateBaseJobInfosIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_baseJobInfosValidator.validate(model_internal::_instance.baseJobInfos)
        model_internal::_baseJobInfosIsValid_der = (valRes.results == null);
        model_internal::_baseJobInfosIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::baseJobInfosValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::baseJobInfosValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get baseJobInfosValidationFailureMessages():Array
    {
        if (model_internal::_baseJobInfosValidationFailureMessages == null)
            model_internal::calculateBaseJobInfosIsValid();

        return _baseJobInfosValidationFailureMessages;
    }

    model_internal function set baseJobInfosValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_baseJobInfosValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_baseJobInfosValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "baseJobInfosValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get DependencyListStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get DependencyListValidator() : StyleValidator
    {
        return model_internal::_DependencyListValidator;
    }

    model_internal function set _DependencyListIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_DependencyListIsValid;         
        if (oldValue !== value)
        {
            model_internal::_DependencyListIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "DependencyListIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get DependencyListIsValid():Boolean
    {
        if (!model_internal::_DependencyListIsValidCacheInitialized)
        {
            model_internal::calculateDependencyListIsValid();
        }

        return model_internal::_DependencyListIsValid;
    }

    model_internal function calculateDependencyListIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_DependencyListValidator.validate(model_internal::_instance.DependencyList)
        model_internal::_DependencyListIsValid_der = (valRes.results == null);
        model_internal::_DependencyListIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::DependencyListValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::DependencyListValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get DependencyListValidationFailureMessages():Array
    {
        if (model_internal::_DependencyListValidationFailureMessages == null)
            model_internal::calculateDependencyListIsValid();

        return _DependencyListValidationFailureMessages;
    }

    model_internal function set DependencyListValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_DependencyListValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_DependencyListValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "DependencyListValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get stateInfosStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get stateInfosValidator() : StyleValidator
    {
        return model_internal::_stateInfosValidator;
    }

    model_internal function set _stateInfosIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_stateInfosIsValid;         
        if (oldValue !== value)
        {
            model_internal::_stateInfosIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "stateInfosIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get stateInfosIsValid():Boolean
    {
        if (!model_internal::_stateInfosIsValidCacheInitialized)
        {
            model_internal::calculateStateInfosIsValid();
        }

        return model_internal::_stateInfosIsValid;
    }

    model_internal function calculateStateInfosIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_stateInfosValidator.validate(model_internal::_instance.stateInfos)
        model_internal::_stateInfosIsValid_der = (valRes.results == null);
        model_internal::_stateInfosIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::stateInfosValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::stateInfosValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get stateInfosValidationFailureMessages():Array
    {
        if (model_internal::_stateInfosValidationFailureMessages == null)
            model_internal::calculateStateInfosIsValid();

        return _stateInfosValidationFailureMessages;
    }

    model_internal function set stateInfosValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_stateInfosValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_stateInfosValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "stateInfosValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get managementStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get managementValidator() : StyleValidator
    {
        return model_internal::_managementValidator;
    }

    model_internal function set _managementIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_managementIsValid;         
        if (oldValue !== value)
        {
            model_internal::_managementIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "managementIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get managementIsValid():Boolean
    {
        if (!model_internal::_managementIsValidCacheInitialized)
        {
            model_internal::calculateManagementIsValid();
        }

        return model_internal::_managementIsValid;
    }

    model_internal function calculateManagementIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_managementValidator.validate(model_internal::_instance.management)
        model_internal::_managementIsValid_der = (valRes.results == null);
        model_internal::_managementIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::managementValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::managementValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get managementValidationFailureMessages():Array
    {
        if (model_internal::_managementValidationFailureMessages == null)
            model_internal::calculateManagementIsValid();

        return _managementValidationFailureMessages;
    }

    model_internal function set managementValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_managementValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_managementValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "managementValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get scheduleInfoStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get scheduleInfoValidator() : StyleValidator
    {
        return model_internal::_scheduleInfoValidator;
    }

    model_internal function set _scheduleInfoIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_scheduleInfoIsValid;         
        if (oldValue !== value)
        {
            model_internal::_scheduleInfoIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "scheduleInfoIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get scheduleInfoIsValid():Boolean
    {
        if (!model_internal::_scheduleInfoIsValidCacheInitialized)
        {
            model_internal::calculateScheduleInfoIsValid();
        }

        return model_internal::_scheduleInfoIsValid;
    }

    model_internal function calculateScheduleInfoIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_scheduleInfoValidator.validate(model_internal::_instance.scheduleInfo)
        model_internal::_scheduleInfoIsValid_der = (valRes.results == null);
        model_internal::_scheduleInfoIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::scheduleInfoValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::scheduleInfoValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get scheduleInfoValidationFailureMessages():Array
    {
        if (model_internal::_scheduleInfoValidationFailureMessages == null)
            model_internal::calculateScheduleInfoIsValid();

        return _scheduleInfoValidationFailureMessages;
    }

    model_internal function set scheduleInfoValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_scheduleInfoValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_scheduleInfoValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "scheduleInfoValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }


     /**
     * 
     * @inheritDoc 
     */ 
     override public function getStyle(propertyName:String):com.adobe.fiber.styles.IStyle
     {
         switch(propertyName)
         {
            default:
            {
                return null;
            }
         }
     }
     
     /**
     * 
     * @inheritDoc 
     *  
     */  
     override public function getPropertyValidationFailureMessages(propertyName:String):Array
     {
         switch(propertyName)
         {
            case("type"):
            {
                return typeValidationFailureMessages;
            }
            case("groupId"):
            {
                return groupIdValidationFailureMessages;
            }
            case("dangerZoneGroupId"):
            {
                return dangerZoneGroupIdValidationFailureMessages;
            }
            case("handlerURI"):
            {
                return handlerURIValidationFailureMessages;
            }
            case("Id"):
            {
                return IdValidationFailureMessages;
            }
            case("agentId"):
            {
                return agentIdValidationFailureMessages;
            }
            case("baseJobInfos"):
            {
                return baseJobInfosValidationFailureMessages;
            }
            case("DependencyList"):
            {
                return DependencyListValidationFailureMessages;
            }
            case("stateInfos"):
            {
                return stateInfosValidationFailureMessages;
            }
            case("management"):
            {
                return managementValidationFailureMessages;
            }
            case("scheduleInfo"):
            {
                return scheduleInfoValidationFailureMessages;
            }
            default:
            {
                return emptyArray;
            }
         }
     }

}

}
