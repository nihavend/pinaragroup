
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
import valueObjects.CascadingConditions;
import valueObjects.PeriodInfo;
import valueObjects.TimeManagement;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IModelType;
import mx.events.PropertyChangeEvent;

use namespace model_internal;

[ExcludeClass]
internal class _ManagementEntityMetadata extends com.adobe.fiber.valueobjects.AbstractEntityMetadata
{
    private static var emptyArray:Array = new Array();

    model_internal static var allProperties:Array = new Array("trigger", "periodInfo", "timeManagement", "cascadingConditions");
    model_internal static var allAssociationProperties:Array = new Array();
    model_internal static var allRequiredProperties:Array = new Array("trigger", "periodInfo", "timeManagement", "cascadingConditions");
    model_internal static var allAlwaysAvailableProperties:Array = new Array("trigger", "periodInfo", "timeManagement", "cascadingConditions");
    model_internal static var guardedProperties:Array = new Array();
    model_internal static var dataProperties:Array = new Array("trigger", "periodInfo", "timeManagement", "cascadingConditions");
    model_internal static var sourceProperties:Array = emptyArray
    model_internal static var nonDerivedProperties:Array = new Array("trigger", "periodInfo", "timeManagement", "cascadingConditions");
    model_internal static var derivedProperties:Array = new Array();
    model_internal static var collectionProperties:Array = new Array();
    model_internal static var collectionBaseMap:Object;
    model_internal static var entityName:String = "Management";
    model_internal static var dependentsOnMap:Object;
    model_internal static var dependedOnServices:Array = new Array();
    model_internal static var propertyTypeMap:Object;

    
    model_internal var _triggerIsValid:Boolean;
    model_internal var _triggerValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _triggerIsValidCacheInitialized:Boolean = false;
    model_internal var _triggerValidationFailureMessages:Array;
    
    model_internal var _periodInfoIsValid:Boolean;
    model_internal var _periodInfoValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _periodInfoIsValidCacheInitialized:Boolean = false;
    model_internal var _periodInfoValidationFailureMessages:Array;
    
    model_internal var _timeManagementIsValid:Boolean;
    model_internal var _timeManagementValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _timeManagementIsValidCacheInitialized:Boolean = false;
    model_internal var _timeManagementValidationFailureMessages:Array;
    
    model_internal var _cascadingConditionsIsValid:Boolean;
    model_internal var _cascadingConditionsValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _cascadingConditionsIsValidCacheInitialized:Boolean = false;
    model_internal var _cascadingConditionsValidationFailureMessages:Array;

    model_internal var _instance:_Super_Management;
    model_internal static var _nullStyle:com.adobe.fiber.styles.Style = new com.adobe.fiber.styles.Style();

    public function _ManagementEntityMetadata(value : _Super_Management)
    {
        // initialize property maps
        if (model_internal::dependentsOnMap == null)
        {
            // dependents map
            model_internal::dependentsOnMap = new Object();
            model_internal::dependentsOnMap["trigger"] = new Array();
            model_internal::dependentsOnMap["periodInfo"] = new Array();
            model_internal::dependentsOnMap["timeManagement"] = new Array();
            model_internal::dependentsOnMap["cascadingConditions"] = new Array();

            // collection base map
            model_internal::collectionBaseMap = new Object();
        }

        // Property type Map
        model_internal::propertyTypeMap = new Object();
        model_internal::propertyTypeMap["trigger"] = "String";
        model_internal::propertyTypeMap["periodInfo"] = "valueObjects.PeriodInfo";
        model_internal::propertyTypeMap["timeManagement"] = "valueObjects.TimeManagement";
        model_internal::propertyTypeMap["cascadingConditions"] = "valueObjects.CascadingConditions";

        model_internal::_instance = value;
        model_internal::_triggerValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForTrigger);
        model_internal::_triggerValidator.required = true;
        model_internal::_triggerValidator.requiredFieldError = "trigger is required";
        //model_internal::_triggerValidator.source = model_internal::_instance;
        //model_internal::_triggerValidator.property = "trigger";
        model_internal::_periodInfoValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForPeriodInfo);
        model_internal::_periodInfoValidator.required = true;
        model_internal::_periodInfoValidator.requiredFieldError = "periodInfo is required";
        //model_internal::_periodInfoValidator.source = model_internal::_instance;
        //model_internal::_periodInfoValidator.property = "periodInfo";
        model_internal::_timeManagementValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForTimeManagement);
        model_internal::_timeManagementValidator.required = true;
        model_internal::_timeManagementValidator.requiredFieldError = "timeManagement is required";
        //model_internal::_timeManagementValidator.source = model_internal::_instance;
        //model_internal::_timeManagementValidator.property = "timeManagement";
        model_internal::_cascadingConditionsValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForCascadingConditions);
        model_internal::_cascadingConditionsValidator.required = true;
        model_internal::_cascadingConditionsValidator.requiredFieldError = "cascadingConditions is required";
        //model_internal::_cascadingConditionsValidator.source = model_internal::_instance;
        //model_internal::_cascadingConditionsValidator.property = "cascadingConditions";
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
            throw new Error(propertyName + " is not a data property of entity Management");
            
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
            throw new Error(propertyName + " is not a collection property of entity Management");

        return model_internal::collectionBaseMap[propertyName];
    }
    
    override public function getPropertyType(propertyName:String):String
    {
        if (model_internal::allProperties.indexOf(propertyName) == -1)
            throw new Error(propertyName + " is not a property of Management");

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
            throw new Error(propertyName + " does not exist for entity Management");
        }

        return model_internal::_instance[propertyName];
    }

    override public function setValue(propertyName:String, value:*):void
    {
        if (model_internal::nonDerivedProperties.indexOf(propertyName) == -1)
        {
            throw new Error(propertyName + " is not a modifiable property of entity Management");
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
            throw new Error(propertyName + " does not exist for entity Management");
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
    public function get isTriggerAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isPeriodInfoAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isTimeManagementAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isCascadingConditionsAvailable():Boolean
    {
        return true;
    }


    /**
     * derived property recalculation
     */
    public function invalidateDependentOnTrigger():void
    {
        if (model_internal::_triggerIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfTrigger = null;
            model_internal::calculateTriggerIsValid();
        }
    }
    public function invalidateDependentOnPeriodInfo():void
    {
        if (model_internal::_periodInfoIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfPeriodInfo = null;
            model_internal::calculatePeriodInfoIsValid();
        }
    }
    public function invalidateDependentOnTimeManagement():void
    {
        if (model_internal::_timeManagementIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfTimeManagement = null;
            model_internal::calculateTimeManagementIsValid();
        }
    }
    public function invalidateDependentOnCascadingConditions():void
    {
        if (model_internal::_cascadingConditionsIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfCascadingConditions = null;
            model_internal::calculateCascadingConditionsIsValid();
        }
    }

    model_internal function fireChangeEvent(propertyName:String, oldValue:Object, newValue:Object):void
    {
        this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, propertyName, oldValue, newValue));
    }

    [Bindable(event="propertyChange")]   
    public function get triggerStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get triggerValidator() : StyleValidator
    {
        return model_internal::_triggerValidator;
    }

    model_internal function set _triggerIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_triggerIsValid;         
        if (oldValue !== value)
        {
            model_internal::_triggerIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "triggerIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get triggerIsValid():Boolean
    {
        if (!model_internal::_triggerIsValidCacheInitialized)
        {
            model_internal::calculateTriggerIsValid();
        }

        return model_internal::_triggerIsValid;
    }

    model_internal function calculateTriggerIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_triggerValidator.validate(model_internal::_instance.trigger)
        model_internal::_triggerIsValid_der = (valRes.results == null);
        model_internal::_triggerIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::triggerValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::triggerValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get triggerValidationFailureMessages():Array
    {
        if (model_internal::_triggerValidationFailureMessages == null)
            model_internal::calculateTriggerIsValid();

        return _triggerValidationFailureMessages;
    }

    model_internal function set triggerValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_triggerValidationFailureMessages;

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
            model_internal::_triggerValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "triggerValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get periodInfoStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get periodInfoValidator() : StyleValidator
    {
        return model_internal::_periodInfoValidator;
    }

    model_internal function set _periodInfoIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_periodInfoIsValid;         
        if (oldValue !== value)
        {
            model_internal::_periodInfoIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "periodInfoIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get periodInfoIsValid():Boolean
    {
        if (!model_internal::_periodInfoIsValidCacheInitialized)
        {
            model_internal::calculatePeriodInfoIsValid();
        }

        return model_internal::_periodInfoIsValid;
    }

    model_internal function calculatePeriodInfoIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_periodInfoValidator.validate(model_internal::_instance.periodInfo)
        model_internal::_periodInfoIsValid_der = (valRes.results == null);
        model_internal::_periodInfoIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::periodInfoValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::periodInfoValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get periodInfoValidationFailureMessages():Array
    {
        if (model_internal::_periodInfoValidationFailureMessages == null)
            model_internal::calculatePeriodInfoIsValid();

        return _periodInfoValidationFailureMessages;
    }

    model_internal function set periodInfoValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_periodInfoValidationFailureMessages;

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
            model_internal::_periodInfoValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "periodInfoValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get timeManagementStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get timeManagementValidator() : StyleValidator
    {
        return model_internal::_timeManagementValidator;
    }

    model_internal function set _timeManagementIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_timeManagementIsValid;         
        if (oldValue !== value)
        {
            model_internal::_timeManagementIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "timeManagementIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get timeManagementIsValid():Boolean
    {
        if (!model_internal::_timeManagementIsValidCacheInitialized)
        {
            model_internal::calculateTimeManagementIsValid();
        }

        return model_internal::_timeManagementIsValid;
    }

    model_internal function calculateTimeManagementIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_timeManagementValidator.validate(model_internal::_instance.timeManagement)
        model_internal::_timeManagementIsValid_der = (valRes.results == null);
        model_internal::_timeManagementIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::timeManagementValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::timeManagementValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get timeManagementValidationFailureMessages():Array
    {
        if (model_internal::_timeManagementValidationFailureMessages == null)
            model_internal::calculateTimeManagementIsValid();

        return _timeManagementValidationFailureMessages;
    }

    model_internal function set timeManagementValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_timeManagementValidationFailureMessages;

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
            model_internal::_timeManagementValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "timeManagementValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get cascadingConditionsStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get cascadingConditionsValidator() : StyleValidator
    {
        return model_internal::_cascadingConditionsValidator;
    }

    model_internal function set _cascadingConditionsIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_cascadingConditionsIsValid;         
        if (oldValue !== value)
        {
            model_internal::_cascadingConditionsIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "cascadingConditionsIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get cascadingConditionsIsValid():Boolean
    {
        if (!model_internal::_cascadingConditionsIsValidCacheInitialized)
        {
            model_internal::calculateCascadingConditionsIsValid();
        }

        return model_internal::_cascadingConditionsIsValid;
    }

    model_internal function calculateCascadingConditionsIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_cascadingConditionsValidator.validate(model_internal::_instance.cascadingConditions)
        model_internal::_cascadingConditionsIsValid_der = (valRes.results == null);
        model_internal::_cascadingConditionsIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::cascadingConditionsValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::cascadingConditionsValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get cascadingConditionsValidationFailureMessages():Array
    {
        if (model_internal::_cascadingConditionsValidationFailureMessages == null)
            model_internal::calculateCascadingConditionsIsValid();

        return _cascadingConditionsValidationFailureMessages;
    }

    model_internal function set cascadingConditionsValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_cascadingConditionsValidationFailureMessages;

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
            model_internal::_cascadingConditionsValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "cascadingConditionsValidationFailureMessages", oldValue, value));
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
            case("trigger"):
            {
                return triggerValidationFailureMessages;
            }
            case("periodInfo"):
            {
                return periodInfoValidationFailureMessages;
            }
            case("timeManagement"):
            {
                return timeManagementValidationFailureMessages;
            }
            case("cascadingConditions"):
            {
                return cascadingConditionsValidationFailureMessages;
            }
            default:
            {
                return emptyArray;
            }
         }
     }

}

}
