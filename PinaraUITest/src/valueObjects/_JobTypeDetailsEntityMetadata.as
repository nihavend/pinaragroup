
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
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IModelType;
import mx.events.PropertyChangeEvent;

use namespace model_internal;

[ExcludeClass]
internal class _JobTypeDetailsEntityMetadata extends com.adobe.fiber.valueobjects.AbstractEntityMetadata
{
    private static var emptyArray:Array = new Array();

    model_internal static var allProperties:Array = new Array("jobCommandType", "jobCommand", "jobPath");
    model_internal static var allAssociationProperties:Array = new Array();
    model_internal static var allRequiredProperties:Array = new Array("jobCommandType", "jobCommand", "jobPath");
    model_internal static var allAlwaysAvailableProperties:Array = new Array("jobCommandType", "jobCommand", "jobPath");
    model_internal static var guardedProperties:Array = new Array();
    model_internal static var dataProperties:Array = new Array("jobCommandType", "jobCommand", "jobPath");
    model_internal static var sourceProperties:Array = emptyArray
    model_internal static var nonDerivedProperties:Array = new Array("jobCommandType", "jobCommand", "jobPath");
    model_internal static var derivedProperties:Array = new Array();
    model_internal static var collectionProperties:Array = new Array();
    model_internal static var collectionBaseMap:Object;
    model_internal static var entityName:String = "JobTypeDetails";
    model_internal static var dependentsOnMap:Object;
    model_internal static var dependedOnServices:Array = new Array();
    model_internal static var propertyTypeMap:Object;

    
    model_internal var _jobCommandTypeIsValid:Boolean;
    model_internal var _jobCommandTypeValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jobCommandTypeIsValidCacheInitialized:Boolean = false;
    model_internal var _jobCommandTypeValidationFailureMessages:Array;
    
    model_internal var _jobCommandIsValid:Boolean;
    model_internal var _jobCommandValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jobCommandIsValidCacheInitialized:Boolean = false;
    model_internal var _jobCommandValidationFailureMessages:Array;
    
    model_internal var _jobPathIsValid:Boolean;
    model_internal var _jobPathValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jobPathIsValidCacheInitialized:Boolean = false;
    model_internal var _jobPathValidationFailureMessages:Array;

    model_internal var _instance:_Super_JobTypeDetails;
    model_internal static var _nullStyle:com.adobe.fiber.styles.Style = new com.adobe.fiber.styles.Style();

    public function _JobTypeDetailsEntityMetadata(value : _Super_JobTypeDetails)
    {
        // initialize property maps
        if (model_internal::dependentsOnMap == null)
        {
            // dependents map
            model_internal::dependentsOnMap = new Object();
            model_internal::dependentsOnMap["jobCommandType"] = new Array();
            model_internal::dependentsOnMap["jobCommand"] = new Array();
            model_internal::dependentsOnMap["jobPath"] = new Array();

            // collection base map
            model_internal::collectionBaseMap = new Object();
        }

        // Property type Map
        model_internal::propertyTypeMap = new Object();
        model_internal::propertyTypeMap["jobCommandType"] = "String";
        model_internal::propertyTypeMap["jobCommand"] = "String";
        model_internal::propertyTypeMap["jobPath"] = "String";

        model_internal::_instance = value;
        model_internal::_jobCommandTypeValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJobCommandType);
        model_internal::_jobCommandTypeValidator.required = true;
        model_internal::_jobCommandTypeValidator.requiredFieldError = "jobCommandType is required";
        //model_internal::_jobCommandTypeValidator.source = model_internal::_instance;
        //model_internal::_jobCommandTypeValidator.property = "jobCommandType";
        model_internal::_jobCommandValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJobCommand);
        model_internal::_jobCommandValidator.required = true;
        model_internal::_jobCommandValidator.requiredFieldError = "jobCommand is required";
        //model_internal::_jobCommandValidator.source = model_internal::_instance;
        //model_internal::_jobCommandValidator.property = "jobCommand";
        model_internal::_jobPathValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJobPath);
        model_internal::_jobPathValidator.required = true;
        model_internal::_jobPathValidator.requiredFieldError = "jobPath is required";
        //model_internal::_jobPathValidator.source = model_internal::_instance;
        //model_internal::_jobPathValidator.property = "jobPath";
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
            throw new Error(propertyName + " is not a data property of entity JobTypeDetails");
            
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
            throw new Error(propertyName + " is not a collection property of entity JobTypeDetails");

        return model_internal::collectionBaseMap[propertyName];
    }
    
    override public function getPropertyType(propertyName:String):String
    {
        if (model_internal::allProperties.indexOf(propertyName) == -1)
            throw new Error(propertyName + " is not a property of JobTypeDetails");

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
            throw new Error(propertyName + " does not exist for entity JobTypeDetails");
        }

        return model_internal::_instance[propertyName];
    }

    override public function setValue(propertyName:String, value:*):void
    {
        if (model_internal::nonDerivedProperties.indexOf(propertyName) == -1)
        {
            throw new Error(propertyName + " is not a modifiable property of entity JobTypeDetails");
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
            throw new Error(propertyName + " does not exist for entity JobTypeDetails");
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
    public function get isJobCommandTypeAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isJobCommandAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isJobPathAvailable():Boolean
    {
        return true;
    }


    /**
     * derived property recalculation
     */
    public function invalidateDependentOnJobCommandType():void
    {
        if (model_internal::_jobCommandTypeIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJobCommandType = null;
            model_internal::calculateJobCommandTypeIsValid();
        }
    }
    public function invalidateDependentOnJobCommand():void
    {
        if (model_internal::_jobCommandIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJobCommand = null;
            model_internal::calculateJobCommandIsValid();
        }
    }
    public function invalidateDependentOnJobPath():void
    {
        if (model_internal::_jobPathIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJobPath = null;
            model_internal::calculateJobPathIsValid();
        }
    }

    model_internal function fireChangeEvent(propertyName:String, oldValue:Object, newValue:Object):void
    {
        this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, propertyName, oldValue, newValue));
    }

    [Bindable(event="propertyChange")]   
    public function get jobCommandTypeStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get jobCommandTypeValidator() : StyleValidator
    {
        return model_internal::_jobCommandTypeValidator;
    }

    model_internal function set _jobCommandTypeIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_jobCommandTypeIsValid;         
        if (oldValue !== value)
        {
            model_internal::_jobCommandTypeIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobCommandTypeIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get jobCommandTypeIsValid():Boolean
    {
        if (!model_internal::_jobCommandTypeIsValidCacheInitialized)
        {
            model_internal::calculateJobCommandTypeIsValid();
        }

        return model_internal::_jobCommandTypeIsValid;
    }

    model_internal function calculateJobCommandTypeIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_jobCommandTypeValidator.validate(model_internal::_instance.jobCommandType)
        model_internal::_jobCommandTypeIsValid_der = (valRes.results == null);
        model_internal::_jobCommandTypeIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::jobCommandTypeValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::jobCommandTypeValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get jobCommandTypeValidationFailureMessages():Array
    {
        if (model_internal::_jobCommandTypeValidationFailureMessages == null)
            model_internal::calculateJobCommandTypeIsValid();

        return _jobCommandTypeValidationFailureMessages;
    }

    model_internal function set jobCommandTypeValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_jobCommandTypeValidationFailureMessages;

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
            model_internal::_jobCommandTypeValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobCommandTypeValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get jobCommandStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get jobCommandValidator() : StyleValidator
    {
        return model_internal::_jobCommandValidator;
    }

    model_internal function set _jobCommandIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_jobCommandIsValid;         
        if (oldValue !== value)
        {
            model_internal::_jobCommandIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobCommandIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get jobCommandIsValid():Boolean
    {
        if (!model_internal::_jobCommandIsValidCacheInitialized)
        {
            model_internal::calculateJobCommandIsValid();
        }

        return model_internal::_jobCommandIsValid;
    }

    model_internal function calculateJobCommandIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_jobCommandValidator.validate(model_internal::_instance.jobCommand)
        model_internal::_jobCommandIsValid_der = (valRes.results == null);
        model_internal::_jobCommandIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::jobCommandValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::jobCommandValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get jobCommandValidationFailureMessages():Array
    {
        if (model_internal::_jobCommandValidationFailureMessages == null)
            model_internal::calculateJobCommandIsValid();

        return _jobCommandValidationFailureMessages;
    }

    model_internal function set jobCommandValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_jobCommandValidationFailureMessages;

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
            model_internal::_jobCommandValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobCommandValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get jobPathStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get jobPathValidator() : StyleValidator
    {
        return model_internal::_jobPathValidator;
    }

    model_internal function set _jobPathIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_jobPathIsValid;         
        if (oldValue !== value)
        {
            model_internal::_jobPathIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobPathIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get jobPathIsValid():Boolean
    {
        if (!model_internal::_jobPathIsValidCacheInitialized)
        {
            model_internal::calculateJobPathIsValid();
        }

        return model_internal::_jobPathIsValid;
    }

    model_internal function calculateJobPathIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_jobPathValidator.validate(model_internal::_instance.jobPath)
        model_internal::_jobPathIsValid_der = (valRes.results == null);
        model_internal::_jobPathIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::jobPathValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::jobPathValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get jobPathValidationFailureMessages():Array
    {
        if (model_internal::_jobPathValidationFailureMessages == null)
            model_internal::calculateJobPathIsValid();

        return _jobPathValidationFailureMessages;
    }

    model_internal function set jobPathValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_jobPathValidationFailureMessages;

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
            model_internal::_jobPathValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobPathValidationFailureMessages", oldValue, value));
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
            case("jobCommandType"):
            {
                return jobCommandTypeValidationFailureMessages;
            }
            case("jobCommand"):
            {
                return jobCommandValidationFailureMessages;
            }
            case("jobPath"):
            {
                return jobPathValidationFailureMessages;
            }
            default:
            {
                return emptyArray;
            }
         }
     }

}

}
