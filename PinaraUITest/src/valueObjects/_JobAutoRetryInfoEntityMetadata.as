
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
internal class _JobAutoRetryInfoEntityMetadata extends com.adobe.fiber.valueobjects.AbstractEntityMetadata
{
    private static var emptyArray:Array = new Array();

    model_internal static var allProperties:Array = new Array("step", "jobAutoRetry");
    model_internal static var allAssociationProperties:Array = new Array();
    model_internal static var allRequiredProperties:Array = new Array("step", "jobAutoRetry");
    model_internal static var allAlwaysAvailableProperties:Array = new Array("step", "jobAutoRetry");
    model_internal static var guardedProperties:Array = new Array();
    model_internal static var dataProperties:Array = new Array("step", "jobAutoRetry");
    model_internal static var sourceProperties:Array = emptyArray
    model_internal static var nonDerivedProperties:Array = new Array("step", "jobAutoRetry");
    model_internal static var derivedProperties:Array = new Array();
    model_internal static var collectionProperties:Array = new Array();
    model_internal static var collectionBaseMap:Object;
    model_internal static var entityName:String = "JobAutoRetryInfo";
    model_internal static var dependentsOnMap:Object;
    model_internal static var dependedOnServices:Array = new Array();
    model_internal static var propertyTypeMap:Object;

    
    model_internal var _stepIsValid:Boolean;
    model_internal var _stepValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _stepIsValidCacheInitialized:Boolean = false;
    model_internal var _stepValidationFailureMessages:Array;
    
    model_internal var _jobAutoRetryIsValid:Boolean;
    model_internal var _jobAutoRetryValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jobAutoRetryIsValidCacheInitialized:Boolean = false;
    model_internal var _jobAutoRetryValidationFailureMessages:Array;

    model_internal var _instance:_Super_JobAutoRetryInfo;
    model_internal static var _nullStyle:com.adobe.fiber.styles.Style = new com.adobe.fiber.styles.Style();

    public function _JobAutoRetryInfoEntityMetadata(value : _Super_JobAutoRetryInfo)
    {
        // initialize property maps
        if (model_internal::dependentsOnMap == null)
        {
            // dependents map
            model_internal::dependentsOnMap = new Object();
            model_internal::dependentsOnMap["step"] = new Array();
            model_internal::dependentsOnMap["jobAutoRetry"] = new Array();

            // collection base map
            model_internal::collectionBaseMap = new Object();
        }

        // Property type Map
        model_internal::propertyTypeMap = new Object();
        model_internal::propertyTypeMap["step"] = "String";
        model_internal::propertyTypeMap["jobAutoRetry"] = "String";

        model_internal::_instance = value;
        model_internal::_stepValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForStep);
        model_internal::_stepValidator.required = true;
        model_internal::_stepValidator.requiredFieldError = "step is required";
        //model_internal::_stepValidator.source = model_internal::_instance;
        //model_internal::_stepValidator.property = "step";
        model_internal::_jobAutoRetryValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJobAutoRetry);
        model_internal::_jobAutoRetryValidator.required = true;
        model_internal::_jobAutoRetryValidator.requiredFieldError = "jobAutoRetry is required";
        //model_internal::_jobAutoRetryValidator.source = model_internal::_instance;
        //model_internal::_jobAutoRetryValidator.property = "jobAutoRetry";
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
            throw new Error(propertyName + " is not a data property of entity JobAutoRetryInfo");
            
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
            throw new Error(propertyName + " is not a collection property of entity JobAutoRetryInfo");

        return model_internal::collectionBaseMap[propertyName];
    }
    
    override public function getPropertyType(propertyName:String):String
    {
        if (model_internal::allProperties.indexOf(propertyName) == -1)
            throw new Error(propertyName + " is not a property of JobAutoRetryInfo");

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
            throw new Error(propertyName + " does not exist for entity JobAutoRetryInfo");
        }

        return model_internal::_instance[propertyName];
    }

    override public function setValue(propertyName:String, value:*):void
    {
        if (model_internal::nonDerivedProperties.indexOf(propertyName) == -1)
        {
            throw new Error(propertyName + " is not a modifiable property of entity JobAutoRetryInfo");
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
            throw new Error(propertyName + " does not exist for entity JobAutoRetryInfo");
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
    public function get isStepAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isJobAutoRetryAvailable():Boolean
    {
        return true;
    }


    /**
     * derived property recalculation
     */
    public function invalidateDependentOnStep():void
    {
        if (model_internal::_stepIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfStep = null;
            model_internal::calculateStepIsValid();
        }
    }
    public function invalidateDependentOnJobAutoRetry():void
    {
        if (model_internal::_jobAutoRetryIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJobAutoRetry = null;
            model_internal::calculateJobAutoRetryIsValid();
        }
    }

    model_internal function fireChangeEvent(propertyName:String, oldValue:Object, newValue:Object):void
    {
        this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, propertyName, oldValue, newValue));
    }

    [Bindable(event="propertyChange")]   
    public function get stepStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get stepValidator() : StyleValidator
    {
        return model_internal::_stepValidator;
    }

    model_internal function set _stepIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_stepIsValid;         
        if (oldValue !== value)
        {
            model_internal::_stepIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "stepIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get stepIsValid():Boolean
    {
        if (!model_internal::_stepIsValidCacheInitialized)
        {
            model_internal::calculateStepIsValid();
        }

        return model_internal::_stepIsValid;
    }

    model_internal function calculateStepIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_stepValidator.validate(model_internal::_instance.step)
        model_internal::_stepIsValid_der = (valRes.results == null);
        model_internal::_stepIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::stepValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::stepValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get stepValidationFailureMessages():Array
    {
        if (model_internal::_stepValidationFailureMessages == null)
            model_internal::calculateStepIsValid();

        return _stepValidationFailureMessages;
    }

    model_internal function set stepValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_stepValidationFailureMessages;

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
            model_internal::_stepValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "stepValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get jobAutoRetryStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get jobAutoRetryValidator() : StyleValidator
    {
        return model_internal::_jobAutoRetryValidator;
    }

    model_internal function set _jobAutoRetryIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_jobAutoRetryIsValid;         
        if (oldValue !== value)
        {
            model_internal::_jobAutoRetryIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobAutoRetryIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get jobAutoRetryIsValid():Boolean
    {
        if (!model_internal::_jobAutoRetryIsValidCacheInitialized)
        {
            model_internal::calculateJobAutoRetryIsValid();
        }

        return model_internal::_jobAutoRetryIsValid;
    }

    model_internal function calculateJobAutoRetryIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_jobAutoRetryValidator.validate(model_internal::_instance.jobAutoRetry)
        model_internal::_jobAutoRetryIsValid_der = (valRes.results == null);
        model_internal::_jobAutoRetryIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::jobAutoRetryValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::jobAutoRetryValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get jobAutoRetryValidationFailureMessages():Array
    {
        if (model_internal::_jobAutoRetryValidationFailureMessages == null)
            model_internal::calculateJobAutoRetryIsValid();

        return _jobAutoRetryValidationFailureMessages;
    }

    model_internal function set jobAutoRetryValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_jobAutoRetryValidationFailureMessages;

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
            model_internal::_jobAutoRetryValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobAutoRetryValidationFailureMessages", oldValue, value));
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
            case("step"):
            {
                return stepValidationFailureMessages;
            }
            case("jobAutoRetry"):
            {
                return jobAutoRetryValidationFailureMessages;
            }
            default:
            {
                return emptyArray;
            }
         }
     }

}

}
