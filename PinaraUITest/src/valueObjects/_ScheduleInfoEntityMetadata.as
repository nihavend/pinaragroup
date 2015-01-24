
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
import mx.collections.ArrayCollection;
import mx.events.ValidationResultEvent;
import valueObjects.DaysOfMonth;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IModelType;
import mx.events.PropertyChangeEvent;

use namespace model_internal;

[ExcludeClass]
internal class _ScheduleInfoEntityMetadata extends com.adobe.fiber.valueobjects.AbstractEntityMetadata
{
    private static var emptyArray:Array = new Array();

    model_internal static var allProperties:Array = new Array("daysOfMonth", "daysOfWeekIntType");
    model_internal static var allAssociationProperties:Array = new Array();
    model_internal static var allRequiredProperties:Array = new Array("daysOfMonth", "daysOfWeekIntType");
    model_internal static var allAlwaysAvailableProperties:Array = new Array("daysOfMonth", "daysOfWeekIntType");
    model_internal static var guardedProperties:Array = new Array();
    model_internal static var dataProperties:Array = new Array("daysOfMonth", "daysOfWeekIntType");
    model_internal static var sourceProperties:Array = emptyArray
    model_internal static var nonDerivedProperties:Array = new Array("daysOfMonth", "daysOfWeekIntType");
    model_internal static var derivedProperties:Array = new Array();
    model_internal static var collectionProperties:Array = new Array("daysOfWeekIntType");
    model_internal static var collectionBaseMap:Object;
    model_internal static var entityName:String = "ScheduleInfo";
    model_internal static var dependentsOnMap:Object;
    model_internal static var dependedOnServices:Array = new Array();
    model_internal static var propertyTypeMap:Object;

    
    model_internal var _daysOfMonthIsValid:Boolean;
    model_internal var _daysOfMonthValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _daysOfMonthIsValidCacheInitialized:Boolean = false;
    model_internal var _daysOfMonthValidationFailureMessages:Array;
    
    model_internal var _daysOfWeekIntTypeIsValid:Boolean;
    model_internal var _daysOfWeekIntTypeValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _daysOfWeekIntTypeIsValidCacheInitialized:Boolean = false;
    model_internal var _daysOfWeekIntTypeValidationFailureMessages:Array;

    model_internal var _instance:_Super_ScheduleInfo;
    model_internal static var _nullStyle:com.adobe.fiber.styles.Style = new com.adobe.fiber.styles.Style();

    public function _ScheduleInfoEntityMetadata(value : _Super_ScheduleInfo)
    {
        // initialize property maps
        if (model_internal::dependentsOnMap == null)
        {
            // dependents map
            model_internal::dependentsOnMap = new Object();
            model_internal::dependentsOnMap["daysOfMonth"] = new Array();
            model_internal::dependentsOnMap["daysOfWeekIntType"] = new Array();

            // collection base map
            model_internal::collectionBaseMap = new Object();
            model_internal::collectionBaseMap["daysOfWeekIntType"] = "String";
        }

        // Property type Map
        model_internal::propertyTypeMap = new Object();
        model_internal::propertyTypeMap["daysOfMonth"] = "valueObjects.DaysOfMonth";
        model_internal::propertyTypeMap["daysOfWeekIntType"] = "ArrayCollection";

        model_internal::_instance = value;
        model_internal::_daysOfMonthValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForDaysOfMonth);
        model_internal::_daysOfMonthValidator.required = true;
        model_internal::_daysOfMonthValidator.requiredFieldError = "daysOfMonth is required";
        //model_internal::_daysOfMonthValidator.source = model_internal::_instance;
        //model_internal::_daysOfMonthValidator.property = "daysOfMonth";
        model_internal::_daysOfWeekIntTypeValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForDaysOfWeekIntType);
        model_internal::_daysOfWeekIntTypeValidator.required = true;
        model_internal::_daysOfWeekIntTypeValidator.requiredFieldError = "daysOfWeekIntType is required";
        //model_internal::_daysOfWeekIntTypeValidator.source = model_internal::_instance;
        //model_internal::_daysOfWeekIntTypeValidator.property = "daysOfWeekIntType";
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
            throw new Error(propertyName + " is not a data property of entity ScheduleInfo");
            
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
            throw new Error(propertyName + " is not a collection property of entity ScheduleInfo");

        return model_internal::collectionBaseMap[propertyName];
    }
    
    override public function getPropertyType(propertyName:String):String
    {
        if (model_internal::allProperties.indexOf(propertyName) == -1)
            throw new Error(propertyName + " is not a property of ScheduleInfo");

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
            throw new Error(propertyName + " does not exist for entity ScheduleInfo");
        }

        return model_internal::_instance[propertyName];
    }

    override public function setValue(propertyName:String, value:*):void
    {
        if (model_internal::nonDerivedProperties.indexOf(propertyName) == -1)
        {
            throw new Error(propertyName + " is not a modifiable property of entity ScheduleInfo");
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
            throw new Error(propertyName + " does not exist for entity ScheduleInfo");
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
    public function get isDaysOfMonthAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isDaysOfWeekIntTypeAvailable():Boolean
    {
        return true;
    }


    /**
     * derived property recalculation
     */
    public function invalidateDependentOnDaysOfMonth():void
    {
        if (model_internal::_daysOfMonthIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfDaysOfMonth = null;
            model_internal::calculateDaysOfMonthIsValid();
        }
    }
    public function invalidateDependentOnDaysOfWeekIntType():void
    {
        if (model_internal::_daysOfWeekIntTypeIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfDaysOfWeekIntType = null;
            model_internal::calculateDaysOfWeekIntTypeIsValid();
        }
    }

    model_internal function fireChangeEvent(propertyName:String, oldValue:Object, newValue:Object):void
    {
        this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, propertyName, oldValue, newValue));
    }

    [Bindable(event="propertyChange")]   
    public function get daysOfMonthStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get daysOfMonthValidator() : StyleValidator
    {
        return model_internal::_daysOfMonthValidator;
    }

    model_internal function set _daysOfMonthIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_daysOfMonthIsValid;         
        if (oldValue !== value)
        {
            model_internal::_daysOfMonthIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "daysOfMonthIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get daysOfMonthIsValid():Boolean
    {
        if (!model_internal::_daysOfMonthIsValidCacheInitialized)
        {
            model_internal::calculateDaysOfMonthIsValid();
        }

        return model_internal::_daysOfMonthIsValid;
    }

    model_internal function calculateDaysOfMonthIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_daysOfMonthValidator.validate(model_internal::_instance.daysOfMonth)
        model_internal::_daysOfMonthIsValid_der = (valRes.results == null);
        model_internal::_daysOfMonthIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::daysOfMonthValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::daysOfMonthValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get daysOfMonthValidationFailureMessages():Array
    {
        if (model_internal::_daysOfMonthValidationFailureMessages == null)
            model_internal::calculateDaysOfMonthIsValid();

        return _daysOfMonthValidationFailureMessages;
    }

    model_internal function set daysOfMonthValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_daysOfMonthValidationFailureMessages;

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
            model_internal::_daysOfMonthValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "daysOfMonthValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get daysOfWeekIntTypeStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get daysOfWeekIntTypeValidator() : StyleValidator
    {
        return model_internal::_daysOfWeekIntTypeValidator;
    }

    model_internal function set _daysOfWeekIntTypeIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_daysOfWeekIntTypeIsValid;         
        if (oldValue !== value)
        {
            model_internal::_daysOfWeekIntTypeIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "daysOfWeekIntTypeIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get daysOfWeekIntTypeIsValid():Boolean
    {
        if (!model_internal::_daysOfWeekIntTypeIsValidCacheInitialized)
        {
            model_internal::calculateDaysOfWeekIntTypeIsValid();
        }

        return model_internal::_daysOfWeekIntTypeIsValid;
    }

    model_internal function calculateDaysOfWeekIntTypeIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_daysOfWeekIntTypeValidator.validate(model_internal::_instance.daysOfWeekIntType)
        model_internal::_daysOfWeekIntTypeIsValid_der = (valRes.results == null);
        model_internal::_daysOfWeekIntTypeIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::daysOfWeekIntTypeValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::daysOfWeekIntTypeValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get daysOfWeekIntTypeValidationFailureMessages():Array
    {
        if (model_internal::_daysOfWeekIntTypeValidationFailureMessages == null)
            model_internal::calculateDaysOfWeekIntTypeIsValid();

        return _daysOfWeekIntTypeValidationFailureMessages;
    }

    model_internal function set daysOfWeekIntTypeValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_daysOfWeekIntTypeValidationFailureMessages;

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
            model_internal::_daysOfWeekIntTypeValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "daysOfWeekIntTypeValidationFailureMessages", oldValue, value));
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
            case("daysOfMonth"):
            {
                return daysOfMonthValidationFailureMessages;
            }
            case("daysOfWeekIntType"):
            {
                return daysOfWeekIntTypeValidationFailureMessages;
            }
            default:
            {
                return emptyArray;
            }
         }
     }

}

}
