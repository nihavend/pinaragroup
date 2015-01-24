
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
import valueObjects.JobTypeDetails;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IModelType;
import mx.events.PropertyChangeEvent;

use namespace model_internal;

[ExcludeClass]
internal class _BaseJobInfosEntityMetadata extends com.adobe.fiber.valueobjects.AbstractEntityMetadata
{
    private static var emptyArray:Array = new Array();

    model_internal static var allProperties:Array = new Array("jsName", "jobTypeDetails", "jobLogFile", "jobLogPath", "oSystem", "jobPriority", "jsIsActive", "userId");
    model_internal static var allAssociationProperties:Array = new Array();
    model_internal static var allRequiredProperties:Array = new Array("jsName", "jobTypeDetails", "jobLogFile", "jobLogPath", "oSystem", "jobPriority", "jsIsActive", "userId");
    model_internal static var allAlwaysAvailableProperties:Array = new Array("jsName", "jobTypeDetails", "jobLogFile", "jobLogPath", "oSystem", "jobPriority", "jsIsActive", "userId");
    model_internal static var guardedProperties:Array = new Array();
    model_internal static var dataProperties:Array = new Array("jsName", "jobTypeDetails", "jobLogFile", "jobLogPath", "oSystem", "jobPriority", "jsIsActive", "userId");
    model_internal static var sourceProperties:Array = emptyArray
    model_internal static var nonDerivedProperties:Array = new Array("jsName", "jobTypeDetails", "jobLogFile", "jobLogPath", "oSystem", "jobPriority", "jsIsActive", "userId");
    model_internal static var derivedProperties:Array = new Array();
    model_internal static var collectionProperties:Array = new Array();
    model_internal static var collectionBaseMap:Object;
    model_internal static var entityName:String = "BaseJobInfos";
    model_internal static var dependentsOnMap:Object;
    model_internal static var dependedOnServices:Array = new Array();
    model_internal static var propertyTypeMap:Object;

    
    model_internal var _jsNameIsValid:Boolean;
    model_internal var _jsNameValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jsNameIsValidCacheInitialized:Boolean = false;
    model_internal var _jsNameValidationFailureMessages:Array;
    
    model_internal var _jobTypeDetailsIsValid:Boolean;
    model_internal var _jobTypeDetailsValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jobTypeDetailsIsValidCacheInitialized:Boolean = false;
    model_internal var _jobTypeDetailsValidationFailureMessages:Array;
    
    model_internal var _jobLogFileIsValid:Boolean;
    model_internal var _jobLogFileValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jobLogFileIsValidCacheInitialized:Boolean = false;
    model_internal var _jobLogFileValidationFailureMessages:Array;
    
    model_internal var _jobLogPathIsValid:Boolean;
    model_internal var _jobLogPathValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jobLogPathIsValidCacheInitialized:Boolean = false;
    model_internal var _jobLogPathValidationFailureMessages:Array;
    
    model_internal var _oSystemIsValid:Boolean;
    model_internal var _oSystemValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _oSystemIsValidCacheInitialized:Boolean = false;
    model_internal var _oSystemValidationFailureMessages:Array;
    
    model_internal var _jobPriorityIsValid:Boolean;
    model_internal var _jobPriorityValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jobPriorityIsValidCacheInitialized:Boolean = false;
    model_internal var _jobPriorityValidationFailureMessages:Array;
    
    model_internal var _jsIsActiveIsValid:Boolean;
    model_internal var _jsIsActiveValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jsIsActiveIsValidCacheInitialized:Boolean = false;
    model_internal var _jsIsActiveValidationFailureMessages:Array;
    
    model_internal var _userIdIsValid:Boolean;
    model_internal var _userIdValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _userIdIsValidCacheInitialized:Boolean = false;
    model_internal var _userIdValidationFailureMessages:Array;

    model_internal var _instance:_Super_BaseJobInfos;
    model_internal static var _nullStyle:com.adobe.fiber.styles.Style = new com.adobe.fiber.styles.Style();

    public function _BaseJobInfosEntityMetadata(value : _Super_BaseJobInfos)
    {
        // initialize property maps
        if (model_internal::dependentsOnMap == null)
        {
            // dependents map
            model_internal::dependentsOnMap = new Object();
            model_internal::dependentsOnMap["jsName"] = new Array();
            model_internal::dependentsOnMap["jobTypeDetails"] = new Array();
            model_internal::dependentsOnMap["jobLogFile"] = new Array();
            model_internal::dependentsOnMap["jobLogPath"] = new Array();
            model_internal::dependentsOnMap["oSystem"] = new Array();
            model_internal::dependentsOnMap["jobPriority"] = new Array();
            model_internal::dependentsOnMap["jsIsActive"] = new Array();
            model_internal::dependentsOnMap["userId"] = new Array();

            // collection base map
            model_internal::collectionBaseMap = new Object();
        }

        // Property type Map
        model_internal::propertyTypeMap = new Object();
        model_internal::propertyTypeMap["jsName"] = "String";
        model_internal::propertyTypeMap["jobTypeDetails"] = "valueObjects.JobTypeDetails";
        model_internal::propertyTypeMap["jobLogFile"] = "String";
        model_internal::propertyTypeMap["jobLogPath"] = "String";
        model_internal::propertyTypeMap["oSystem"] = "String";
        model_internal::propertyTypeMap["jobPriority"] = "String";
        model_internal::propertyTypeMap["jsIsActive"] = "String";
        model_internal::propertyTypeMap["userId"] = "String";

        model_internal::_instance = value;
        model_internal::_jsNameValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJsName);
        model_internal::_jsNameValidator.required = true;
        model_internal::_jsNameValidator.requiredFieldError = "jsName is required";
        //model_internal::_jsNameValidator.source = model_internal::_instance;
        //model_internal::_jsNameValidator.property = "jsName";
        model_internal::_jobTypeDetailsValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJobTypeDetails);
        model_internal::_jobTypeDetailsValidator.required = true;
        model_internal::_jobTypeDetailsValidator.requiredFieldError = "jobTypeDetails is required";
        //model_internal::_jobTypeDetailsValidator.source = model_internal::_instance;
        //model_internal::_jobTypeDetailsValidator.property = "jobTypeDetails";
        model_internal::_jobLogFileValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJobLogFile);
        model_internal::_jobLogFileValidator.required = true;
        model_internal::_jobLogFileValidator.requiredFieldError = "jobLogFile is required";
        //model_internal::_jobLogFileValidator.source = model_internal::_instance;
        //model_internal::_jobLogFileValidator.property = "jobLogFile";
        model_internal::_jobLogPathValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJobLogPath);
        model_internal::_jobLogPathValidator.required = true;
        model_internal::_jobLogPathValidator.requiredFieldError = "jobLogPath is required";
        //model_internal::_jobLogPathValidator.source = model_internal::_instance;
        //model_internal::_jobLogPathValidator.property = "jobLogPath";
        model_internal::_oSystemValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForOSystem);
        model_internal::_oSystemValidator.required = true;
        model_internal::_oSystemValidator.requiredFieldError = "oSystem is required";
        //model_internal::_oSystemValidator.source = model_internal::_instance;
        //model_internal::_oSystemValidator.property = "oSystem";
        model_internal::_jobPriorityValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJobPriority);
        model_internal::_jobPriorityValidator.required = true;
        model_internal::_jobPriorityValidator.requiredFieldError = "jobPriority is required";
        //model_internal::_jobPriorityValidator.source = model_internal::_instance;
        //model_internal::_jobPriorityValidator.property = "jobPriority";
        model_internal::_jsIsActiveValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJsIsActive);
        model_internal::_jsIsActiveValidator.required = true;
        model_internal::_jsIsActiveValidator.requiredFieldError = "jsIsActive is required";
        //model_internal::_jsIsActiveValidator.source = model_internal::_instance;
        //model_internal::_jsIsActiveValidator.property = "jsIsActive";
        model_internal::_userIdValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForUserId);
        model_internal::_userIdValidator.required = true;
        model_internal::_userIdValidator.requiredFieldError = "userId is required";
        //model_internal::_userIdValidator.source = model_internal::_instance;
        //model_internal::_userIdValidator.property = "userId";
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
            throw new Error(propertyName + " is not a data property of entity BaseJobInfos");
            
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
            throw new Error(propertyName + " is not a collection property of entity BaseJobInfos");

        return model_internal::collectionBaseMap[propertyName];
    }
    
    override public function getPropertyType(propertyName:String):String
    {
        if (model_internal::allProperties.indexOf(propertyName) == -1)
            throw new Error(propertyName + " is not a property of BaseJobInfos");

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
            throw new Error(propertyName + " does not exist for entity BaseJobInfos");
        }

        return model_internal::_instance[propertyName];
    }

    override public function setValue(propertyName:String, value:*):void
    {
        if (model_internal::nonDerivedProperties.indexOf(propertyName) == -1)
        {
            throw new Error(propertyName + " is not a modifiable property of entity BaseJobInfos");
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
            throw new Error(propertyName + " does not exist for entity BaseJobInfos");
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
    public function get isJsNameAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isJobTypeDetailsAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isJobLogFileAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isJobLogPathAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isOSystemAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isJobPriorityAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isJsIsActiveAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isUserIdAvailable():Boolean
    {
        return true;
    }


    /**
     * derived property recalculation
     */
    public function invalidateDependentOnJsName():void
    {
        if (model_internal::_jsNameIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJsName = null;
            model_internal::calculateJsNameIsValid();
        }
    }
    public function invalidateDependentOnJobTypeDetails():void
    {
        if (model_internal::_jobTypeDetailsIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJobTypeDetails = null;
            model_internal::calculateJobTypeDetailsIsValid();
        }
    }
    public function invalidateDependentOnJobLogFile():void
    {
        if (model_internal::_jobLogFileIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJobLogFile = null;
            model_internal::calculateJobLogFileIsValid();
        }
    }
    public function invalidateDependentOnJobLogPath():void
    {
        if (model_internal::_jobLogPathIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJobLogPath = null;
            model_internal::calculateJobLogPathIsValid();
        }
    }
    public function invalidateDependentOnOSystem():void
    {
        if (model_internal::_oSystemIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfOSystem = null;
            model_internal::calculateOSystemIsValid();
        }
    }
    public function invalidateDependentOnJobPriority():void
    {
        if (model_internal::_jobPriorityIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJobPriority = null;
            model_internal::calculateJobPriorityIsValid();
        }
    }
    public function invalidateDependentOnJsIsActive():void
    {
        if (model_internal::_jsIsActiveIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJsIsActive = null;
            model_internal::calculateJsIsActiveIsValid();
        }
    }
    public function invalidateDependentOnUserId():void
    {
        if (model_internal::_userIdIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfUserId = null;
            model_internal::calculateUserIdIsValid();
        }
    }

    model_internal function fireChangeEvent(propertyName:String, oldValue:Object, newValue:Object):void
    {
        this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, propertyName, oldValue, newValue));
    }

    [Bindable(event="propertyChange")]   
    public function get jsNameStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get jsNameValidator() : StyleValidator
    {
        return model_internal::_jsNameValidator;
    }

    model_internal function set _jsNameIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_jsNameIsValid;         
        if (oldValue !== value)
        {
            model_internal::_jsNameIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsNameIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get jsNameIsValid():Boolean
    {
        if (!model_internal::_jsNameIsValidCacheInitialized)
        {
            model_internal::calculateJsNameIsValid();
        }

        return model_internal::_jsNameIsValid;
    }

    model_internal function calculateJsNameIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_jsNameValidator.validate(model_internal::_instance.jsName)
        model_internal::_jsNameIsValid_der = (valRes.results == null);
        model_internal::_jsNameIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::jsNameValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::jsNameValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get jsNameValidationFailureMessages():Array
    {
        if (model_internal::_jsNameValidationFailureMessages == null)
            model_internal::calculateJsNameIsValid();

        return _jsNameValidationFailureMessages;
    }

    model_internal function set jsNameValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_jsNameValidationFailureMessages;

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
            model_internal::_jsNameValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsNameValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get jobTypeDetailsStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get jobTypeDetailsValidator() : StyleValidator
    {
        return model_internal::_jobTypeDetailsValidator;
    }

    model_internal function set _jobTypeDetailsIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_jobTypeDetailsIsValid;         
        if (oldValue !== value)
        {
            model_internal::_jobTypeDetailsIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobTypeDetailsIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get jobTypeDetailsIsValid():Boolean
    {
        if (!model_internal::_jobTypeDetailsIsValidCacheInitialized)
        {
            model_internal::calculateJobTypeDetailsIsValid();
        }

        return model_internal::_jobTypeDetailsIsValid;
    }

    model_internal function calculateJobTypeDetailsIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_jobTypeDetailsValidator.validate(model_internal::_instance.jobTypeDetails)
        model_internal::_jobTypeDetailsIsValid_der = (valRes.results == null);
        model_internal::_jobTypeDetailsIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::jobTypeDetailsValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::jobTypeDetailsValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get jobTypeDetailsValidationFailureMessages():Array
    {
        if (model_internal::_jobTypeDetailsValidationFailureMessages == null)
            model_internal::calculateJobTypeDetailsIsValid();

        return _jobTypeDetailsValidationFailureMessages;
    }

    model_internal function set jobTypeDetailsValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_jobTypeDetailsValidationFailureMessages;

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
            model_internal::_jobTypeDetailsValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobTypeDetailsValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get jobLogFileStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get jobLogFileValidator() : StyleValidator
    {
        return model_internal::_jobLogFileValidator;
    }

    model_internal function set _jobLogFileIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_jobLogFileIsValid;         
        if (oldValue !== value)
        {
            model_internal::_jobLogFileIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobLogFileIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get jobLogFileIsValid():Boolean
    {
        if (!model_internal::_jobLogFileIsValidCacheInitialized)
        {
            model_internal::calculateJobLogFileIsValid();
        }

        return model_internal::_jobLogFileIsValid;
    }

    model_internal function calculateJobLogFileIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_jobLogFileValidator.validate(model_internal::_instance.jobLogFile)
        model_internal::_jobLogFileIsValid_der = (valRes.results == null);
        model_internal::_jobLogFileIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::jobLogFileValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::jobLogFileValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get jobLogFileValidationFailureMessages():Array
    {
        if (model_internal::_jobLogFileValidationFailureMessages == null)
            model_internal::calculateJobLogFileIsValid();

        return _jobLogFileValidationFailureMessages;
    }

    model_internal function set jobLogFileValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_jobLogFileValidationFailureMessages;

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
            model_internal::_jobLogFileValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobLogFileValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get jobLogPathStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get jobLogPathValidator() : StyleValidator
    {
        return model_internal::_jobLogPathValidator;
    }

    model_internal function set _jobLogPathIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_jobLogPathIsValid;         
        if (oldValue !== value)
        {
            model_internal::_jobLogPathIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobLogPathIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get jobLogPathIsValid():Boolean
    {
        if (!model_internal::_jobLogPathIsValidCacheInitialized)
        {
            model_internal::calculateJobLogPathIsValid();
        }

        return model_internal::_jobLogPathIsValid;
    }

    model_internal function calculateJobLogPathIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_jobLogPathValidator.validate(model_internal::_instance.jobLogPath)
        model_internal::_jobLogPathIsValid_der = (valRes.results == null);
        model_internal::_jobLogPathIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::jobLogPathValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::jobLogPathValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get jobLogPathValidationFailureMessages():Array
    {
        if (model_internal::_jobLogPathValidationFailureMessages == null)
            model_internal::calculateJobLogPathIsValid();

        return _jobLogPathValidationFailureMessages;
    }

    model_internal function set jobLogPathValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_jobLogPathValidationFailureMessages;

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
            model_internal::_jobLogPathValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobLogPathValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get oSystemStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get oSystemValidator() : StyleValidator
    {
        return model_internal::_oSystemValidator;
    }

    model_internal function set _oSystemIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_oSystemIsValid;         
        if (oldValue !== value)
        {
            model_internal::_oSystemIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "oSystemIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get oSystemIsValid():Boolean
    {
        if (!model_internal::_oSystemIsValidCacheInitialized)
        {
            model_internal::calculateOSystemIsValid();
        }

        return model_internal::_oSystemIsValid;
    }

    model_internal function calculateOSystemIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_oSystemValidator.validate(model_internal::_instance.oSystem)
        model_internal::_oSystemIsValid_der = (valRes.results == null);
        model_internal::_oSystemIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::oSystemValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::oSystemValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get oSystemValidationFailureMessages():Array
    {
        if (model_internal::_oSystemValidationFailureMessages == null)
            model_internal::calculateOSystemIsValid();

        return _oSystemValidationFailureMessages;
    }

    model_internal function set oSystemValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_oSystemValidationFailureMessages;

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
            model_internal::_oSystemValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "oSystemValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get jobPriorityStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get jobPriorityValidator() : StyleValidator
    {
        return model_internal::_jobPriorityValidator;
    }

    model_internal function set _jobPriorityIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_jobPriorityIsValid;         
        if (oldValue !== value)
        {
            model_internal::_jobPriorityIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobPriorityIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get jobPriorityIsValid():Boolean
    {
        if (!model_internal::_jobPriorityIsValidCacheInitialized)
        {
            model_internal::calculateJobPriorityIsValid();
        }

        return model_internal::_jobPriorityIsValid;
    }

    model_internal function calculateJobPriorityIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_jobPriorityValidator.validate(model_internal::_instance.jobPriority)
        model_internal::_jobPriorityIsValid_der = (valRes.results == null);
        model_internal::_jobPriorityIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::jobPriorityValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::jobPriorityValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get jobPriorityValidationFailureMessages():Array
    {
        if (model_internal::_jobPriorityValidationFailureMessages == null)
            model_internal::calculateJobPriorityIsValid();

        return _jobPriorityValidationFailureMessages;
    }

    model_internal function set jobPriorityValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_jobPriorityValidationFailureMessages;

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
            model_internal::_jobPriorityValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobPriorityValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get jsIsActiveStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get jsIsActiveValidator() : StyleValidator
    {
        return model_internal::_jsIsActiveValidator;
    }

    model_internal function set _jsIsActiveIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_jsIsActiveIsValid;         
        if (oldValue !== value)
        {
            model_internal::_jsIsActiveIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsIsActiveIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get jsIsActiveIsValid():Boolean
    {
        if (!model_internal::_jsIsActiveIsValidCacheInitialized)
        {
            model_internal::calculateJsIsActiveIsValid();
        }

        return model_internal::_jsIsActiveIsValid;
    }

    model_internal function calculateJsIsActiveIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_jsIsActiveValidator.validate(model_internal::_instance.jsIsActive)
        model_internal::_jsIsActiveIsValid_der = (valRes.results == null);
        model_internal::_jsIsActiveIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::jsIsActiveValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::jsIsActiveValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get jsIsActiveValidationFailureMessages():Array
    {
        if (model_internal::_jsIsActiveValidationFailureMessages == null)
            model_internal::calculateJsIsActiveIsValid();

        return _jsIsActiveValidationFailureMessages;
    }

    model_internal function set jsIsActiveValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_jsIsActiveValidationFailureMessages;

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
            model_internal::_jsIsActiveValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsIsActiveValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get userIdStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get userIdValidator() : StyleValidator
    {
        return model_internal::_userIdValidator;
    }

    model_internal function set _userIdIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_userIdIsValid;         
        if (oldValue !== value)
        {
            model_internal::_userIdIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "userIdIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get userIdIsValid():Boolean
    {
        if (!model_internal::_userIdIsValidCacheInitialized)
        {
            model_internal::calculateUserIdIsValid();
        }

        return model_internal::_userIdIsValid;
    }

    model_internal function calculateUserIdIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_userIdValidator.validate(model_internal::_instance.userId)
        model_internal::_userIdIsValid_der = (valRes.results == null);
        model_internal::_userIdIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::userIdValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::userIdValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get userIdValidationFailureMessages():Array
    {
        if (model_internal::_userIdValidationFailureMessages == null)
            model_internal::calculateUserIdIsValid();

        return _userIdValidationFailureMessages;
    }

    model_internal function set userIdValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_userIdValidationFailureMessages;

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
            model_internal::_userIdValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "userIdValidationFailureMessages", oldValue, value));
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
            case("jsName"):
            {
                return jsNameValidationFailureMessages;
            }
            case("jobTypeDetails"):
            {
                return jobTypeDetailsValidationFailureMessages;
            }
            case("jobLogFile"):
            {
                return jobLogFileValidationFailureMessages;
            }
            case("jobLogPath"):
            {
                return jobLogPathValidationFailureMessages;
            }
            case("oSystem"):
            {
                return oSystemValidationFailureMessages;
            }
            case("jobPriority"):
            {
                return jobPriorityValidationFailureMessages;
            }
            case("jsIsActive"):
            {
                return jsIsActiveValidationFailureMessages;
            }
            case("userId"):
            {
                return userIdValidationFailureMessages;
            }
            default:
            {
                return emptyArray;
            }
         }
     }

}

}
