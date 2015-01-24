
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
import valueObjects.JobAutoRetryInfo;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IModelType;
import mx.events.PropertyChangeEvent;

use namespace model_internal;

[ExcludeClass]
internal class _CascadingConditionsEntityMetadata extends com.adobe.fiber.valueobjects.AbstractEntityMetadata
{
    private static var emptyArray:Array = new Array();

    model_internal static var allProperties:Array = new Array("runEvenIfFailed", "jobSafeToRestart", "jobAutoRetryInfo");
    model_internal static var allAssociationProperties:Array = new Array();
    model_internal static var allRequiredProperties:Array = new Array("runEvenIfFailed", "jobSafeToRestart", "jobAutoRetryInfo");
    model_internal static var allAlwaysAvailableProperties:Array = new Array("runEvenIfFailed", "jobSafeToRestart", "jobAutoRetryInfo");
    model_internal static var guardedProperties:Array = new Array();
    model_internal static var dataProperties:Array = new Array("runEvenIfFailed", "jobSafeToRestart", "jobAutoRetryInfo");
    model_internal static var sourceProperties:Array = emptyArray
    model_internal static var nonDerivedProperties:Array = new Array("runEvenIfFailed", "jobSafeToRestart", "jobAutoRetryInfo");
    model_internal static var derivedProperties:Array = new Array();
    model_internal static var collectionProperties:Array = new Array();
    model_internal static var collectionBaseMap:Object;
    model_internal static var entityName:String = "CascadingConditions";
    model_internal static var dependentsOnMap:Object;
    model_internal static var dependedOnServices:Array = new Array();
    model_internal static var propertyTypeMap:Object;

    
    model_internal var _runEvenIfFailedIsValid:Boolean;
    model_internal var _runEvenIfFailedValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _runEvenIfFailedIsValidCacheInitialized:Boolean = false;
    model_internal var _runEvenIfFailedValidationFailureMessages:Array;
    
    model_internal var _jobSafeToRestartIsValid:Boolean;
    model_internal var _jobSafeToRestartValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jobSafeToRestartIsValidCacheInitialized:Boolean = false;
    model_internal var _jobSafeToRestartValidationFailureMessages:Array;
    
    model_internal var _jobAutoRetryInfoIsValid:Boolean;
    model_internal var _jobAutoRetryInfoValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jobAutoRetryInfoIsValidCacheInitialized:Boolean = false;
    model_internal var _jobAutoRetryInfoValidationFailureMessages:Array;

    model_internal var _instance:_Super_CascadingConditions;
    model_internal static var _nullStyle:com.adobe.fiber.styles.Style = new com.adobe.fiber.styles.Style();

    public function _CascadingConditionsEntityMetadata(value : _Super_CascadingConditions)
    {
        // initialize property maps
        if (model_internal::dependentsOnMap == null)
        {
            // dependents map
            model_internal::dependentsOnMap = new Object();
            model_internal::dependentsOnMap["runEvenIfFailed"] = new Array();
            model_internal::dependentsOnMap["jobSafeToRestart"] = new Array();
            model_internal::dependentsOnMap["jobAutoRetryInfo"] = new Array();

            // collection base map
            model_internal::collectionBaseMap = new Object();
        }

        // Property type Map
        model_internal::propertyTypeMap = new Object();
        model_internal::propertyTypeMap["runEvenIfFailed"] = "String";
        model_internal::propertyTypeMap["jobSafeToRestart"] = "String";
        model_internal::propertyTypeMap["jobAutoRetryInfo"] = "valueObjects.JobAutoRetryInfo";

        model_internal::_instance = value;
        model_internal::_runEvenIfFailedValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForRunEvenIfFailed);
        model_internal::_runEvenIfFailedValidator.required = true;
        model_internal::_runEvenIfFailedValidator.requiredFieldError = "runEvenIfFailed is required";
        //model_internal::_runEvenIfFailedValidator.source = model_internal::_instance;
        //model_internal::_runEvenIfFailedValidator.property = "runEvenIfFailed";
        model_internal::_jobSafeToRestartValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJobSafeToRestart);
        model_internal::_jobSafeToRestartValidator.required = true;
        model_internal::_jobSafeToRestartValidator.requiredFieldError = "jobSafeToRestart is required";
        //model_internal::_jobSafeToRestartValidator.source = model_internal::_instance;
        //model_internal::_jobSafeToRestartValidator.property = "jobSafeToRestart";
        model_internal::_jobAutoRetryInfoValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJobAutoRetryInfo);
        model_internal::_jobAutoRetryInfoValidator.required = true;
        model_internal::_jobAutoRetryInfoValidator.requiredFieldError = "jobAutoRetryInfo is required";
        //model_internal::_jobAutoRetryInfoValidator.source = model_internal::_instance;
        //model_internal::_jobAutoRetryInfoValidator.property = "jobAutoRetryInfo";
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
            throw new Error(propertyName + " is not a data property of entity CascadingConditions");
            
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
            throw new Error(propertyName + " is not a collection property of entity CascadingConditions");

        return model_internal::collectionBaseMap[propertyName];
    }
    
    override public function getPropertyType(propertyName:String):String
    {
        if (model_internal::allProperties.indexOf(propertyName) == -1)
            throw new Error(propertyName + " is not a property of CascadingConditions");

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
            throw new Error(propertyName + " does not exist for entity CascadingConditions");
        }

        return model_internal::_instance[propertyName];
    }

    override public function setValue(propertyName:String, value:*):void
    {
        if (model_internal::nonDerivedProperties.indexOf(propertyName) == -1)
        {
            throw new Error(propertyName + " is not a modifiable property of entity CascadingConditions");
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
            throw new Error(propertyName + " does not exist for entity CascadingConditions");
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
    public function get isRunEvenIfFailedAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isJobSafeToRestartAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isJobAutoRetryInfoAvailable():Boolean
    {
        return true;
    }


    /**
     * derived property recalculation
     */
    public function invalidateDependentOnRunEvenIfFailed():void
    {
        if (model_internal::_runEvenIfFailedIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfRunEvenIfFailed = null;
            model_internal::calculateRunEvenIfFailedIsValid();
        }
    }
    public function invalidateDependentOnJobSafeToRestart():void
    {
        if (model_internal::_jobSafeToRestartIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJobSafeToRestart = null;
            model_internal::calculateJobSafeToRestartIsValid();
        }
    }
    public function invalidateDependentOnJobAutoRetryInfo():void
    {
        if (model_internal::_jobAutoRetryInfoIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJobAutoRetryInfo = null;
            model_internal::calculateJobAutoRetryInfoIsValid();
        }
    }

    model_internal function fireChangeEvent(propertyName:String, oldValue:Object, newValue:Object):void
    {
        this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, propertyName, oldValue, newValue));
    }

    [Bindable(event="propertyChange")]   
    public function get runEvenIfFailedStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get runEvenIfFailedValidator() : StyleValidator
    {
        return model_internal::_runEvenIfFailedValidator;
    }

    model_internal function set _runEvenIfFailedIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_runEvenIfFailedIsValid;         
        if (oldValue !== value)
        {
            model_internal::_runEvenIfFailedIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "runEvenIfFailedIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get runEvenIfFailedIsValid():Boolean
    {
        if (!model_internal::_runEvenIfFailedIsValidCacheInitialized)
        {
            model_internal::calculateRunEvenIfFailedIsValid();
        }

        return model_internal::_runEvenIfFailedIsValid;
    }

    model_internal function calculateRunEvenIfFailedIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_runEvenIfFailedValidator.validate(model_internal::_instance.runEvenIfFailed)
        model_internal::_runEvenIfFailedIsValid_der = (valRes.results == null);
        model_internal::_runEvenIfFailedIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::runEvenIfFailedValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::runEvenIfFailedValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get runEvenIfFailedValidationFailureMessages():Array
    {
        if (model_internal::_runEvenIfFailedValidationFailureMessages == null)
            model_internal::calculateRunEvenIfFailedIsValid();

        return _runEvenIfFailedValidationFailureMessages;
    }

    model_internal function set runEvenIfFailedValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_runEvenIfFailedValidationFailureMessages;

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
            model_internal::_runEvenIfFailedValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "runEvenIfFailedValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get jobSafeToRestartStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get jobSafeToRestartValidator() : StyleValidator
    {
        return model_internal::_jobSafeToRestartValidator;
    }

    model_internal function set _jobSafeToRestartIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_jobSafeToRestartIsValid;         
        if (oldValue !== value)
        {
            model_internal::_jobSafeToRestartIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobSafeToRestartIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get jobSafeToRestartIsValid():Boolean
    {
        if (!model_internal::_jobSafeToRestartIsValidCacheInitialized)
        {
            model_internal::calculateJobSafeToRestartIsValid();
        }

        return model_internal::_jobSafeToRestartIsValid;
    }

    model_internal function calculateJobSafeToRestartIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_jobSafeToRestartValidator.validate(model_internal::_instance.jobSafeToRestart)
        model_internal::_jobSafeToRestartIsValid_der = (valRes.results == null);
        model_internal::_jobSafeToRestartIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::jobSafeToRestartValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::jobSafeToRestartValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get jobSafeToRestartValidationFailureMessages():Array
    {
        if (model_internal::_jobSafeToRestartValidationFailureMessages == null)
            model_internal::calculateJobSafeToRestartIsValid();

        return _jobSafeToRestartValidationFailureMessages;
    }

    model_internal function set jobSafeToRestartValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_jobSafeToRestartValidationFailureMessages;

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
            model_internal::_jobSafeToRestartValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobSafeToRestartValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get jobAutoRetryInfoStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get jobAutoRetryInfoValidator() : StyleValidator
    {
        return model_internal::_jobAutoRetryInfoValidator;
    }

    model_internal function set _jobAutoRetryInfoIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_jobAutoRetryInfoIsValid;         
        if (oldValue !== value)
        {
            model_internal::_jobAutoRetryInfoIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobAutoRetryInfoIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get jobAutoRetryInfoIsValid():Boolean
    {
        if (!model_internal::_jobAutoRetryInfoIsValidCacheInitialized)
        {
            model_internal::calculateJobAutoRetryInfoIsValid();
        }

        return model_internal::_jobAutoRetryInfoIsValid;
    }

    model_internal function calculateJobAutoRetryInfoIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_jobAutoRetryInfoValidator.validate(model_internal::_instance.jobAutoRetryInfo)
        model_internal::_jobAutoRetryInfoIsValid_der = (valRes.results == null);
        model_internal::_jobAutoRetryInfoIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::jobAutoRetryInfoValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::jobAutoRetryInfoValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get jobAutoRetryInfoValidationFailureMessages():Array
    {
        if (model_internal::_jobAutoRetryInfoValidationFailureMessages == null)
            model_internal::calculateJobAutoRetryInfoIsValid();

        return _jobAutoRetryInfoValidationFailureMessages;
    }

    model_internal function set jobAutoRetryInfoValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_jobAutoRetryInfoValidationFailureMessages;

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
            model_internal::_jobAutoRetryInfoValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jobAutoRetryInfoValidationFailureMessages", oldValue, value));
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
            case("runEvenIfFailed"):
            {
                return runEvenIfFailedValidationFailureMessages;
            }
            case("jobSafeToRestart"):
            {
                return jobSafeToRestartValidationFailureMessages;
            }
            case("jobAutoRetryInfo"):
            {
                return jobAutoRetryInfoValidationFailureMessages;
            }
            default:
            {
                return emptyArray;
            }
         }
     }

}

}
