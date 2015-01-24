
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
internal class _JsTimeOutEntityMetadata extends com.adobe.fiber.valueobjects.AbstractEntityMetadata
{
    private static var emptyArray:Array = new Array();

    model_internal static var allProperties:Array = new Array("value_integer", "unit");
    model_internal static var allAssociationProperties:Array = new Array();
    model_internal static var allRequiredProperties:Array = new Array("value_integer", "unit");
    model_internal static var allAlwaysAvailableProperties:Array = new Array("value_integer", "unit");
    model_internal static var guardedProperties:Array = new Array();
    model_internal static var dataProperties:Array = new Array("value_integer", "unit");
    model_internal static var sourceProperties:Array = emptyArray
    model_internal static var nonDerivedProperties:Array = new Array("value_integer", "unit");
    model_internal static var derivedProperties:Array = new Array();
    model_internal static var collectionProperties:Array = new Array();
    model_internal static var collectionBaseMap:Object;
    model_internal static var entityName:String = "JsTimeOut";
    model_internal static var dependentsOnMap:Object;
    model_internal static var dependedOnServices:Array = new Array();
    model_internal static var propertyTypeMap:Object;

    
    model_internal var _value_integerIsValid:Boolean;
    model_internal var _value_integerValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _value_integerIsValidCacheInitialized:Boolean = false;
    model_internal var _value_integerValidationFailureMessages:Array;
    
    model_internal var _unitIsValid:Boolean;
    model_internal var _unitValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _unitIsValidCacheInitialized:Boolean = false;
    model_internal var _unitValidationFailureMessages:Array;

    model_internal var _instance:_Super_JsTimeOut;
    model_internal static var _nullStyle:com.adobe.fiber.styles.Style = new com.adobe.fiber.styles.Style();

    public function _JsTimeOutEntityMetadata(value : _Super_JsTimeOut)
    {
        // initialize property maps
        if (model_internal::dependentsOnMap == null)
        {
            // dependents map
            model_internal::dependentsOnMap = new Object();
            model_internal::dependentsOnMap["value_integer"] = new Array();
            model_internal::dependentsOnMap["unit"] = new Array();

            // collection base map
            model_internal::collectionBaseMap = new Object();
        }

        // Property type Map
        model_internal::propertyTypeMap = new Object();
        model_internal::propertyTypeMap["value_integer"] = "String";
        model_internal::propertyTypeMap["unit"] = "String";

        model_internal::_instance = value;
        model_internal::_value_integerValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForValue_integer);
        model_internal::_value_integerValidator.required = true;
        model_internal::_value_integerValidator.requiredFieldError = "value_integer is required";
        //model_internal::_value_integerValidator.source = model_internal::_instance;
        //model_internal::_value_integerValidator.property = "value_integer";
        model_internal::_unitValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForUnit);
        model_internal::_unitValidator.required = true;
        model_internal::_unitValidator.requiredFieldError = "unit is required";
        //model_internal::_unitValidator.source = model_internal::_instance;
        //model_internal::_unitValidator.property = "unit";
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
            throw new Error(propertyName + " is not a data property of entity JsTimeOut");
            
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
            throw new Error(propertyName + " is not a collection property of entity JsTimeOut");

        return model_internal::collectionBaseMap[propertyName];
    }
    
    override public function getPropertyType(propertyName:String):String
    {
        if (model_internal::allProperties.indexOf(propertyName) == -1)
            throw new Error(propertyName + " is not a property of JsTimeOut");

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
            throw new Error(propertyName + " does not exist for entity JsTimeOut");
        }

        return model_internal::_instance[propertyName];
    }

    override public function setValue(propertyName:String, value:*):void
    {
        if (model_internal::nonDerivedProperties.indexOf(propertyName) == -1)
        {
            throw new Error(propertyName + " is not a modifiable property of entity JsTimeOut");
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
            throw new Error(propertyName + " does not exist for entity JsTimeOut");
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
    public function get isValue_integerAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isUnitAvailable():Boolean
    {
        return true;
    }


    /**
     * derived property recalculation
     */
    public function invalidateDependentOnValue_integer():void
    {
        if (model_internal::_value_integerIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfValue_integer = null;
            model_internal::calculateValue_integerIsValid();
        }
    }
    public function invalidateDependentOnUnit():void
    {
        if (model_internal::_unitIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfUnit = null;
            model_internal::calculateUnitIsValid();
        }
    }

    model_internal function fireChangeEvent(propertyName:String, oldValue:Object, newValue:Object):void
    {
        this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, propertyName, oldValue, newValue));
    }

    [Bindable(event="propertyChange")]   
    public function get value_integerStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get value_integerValidator() : StyleValidator
    {
        return model_internal::_value_integerValidator;
    }

    model_internal function set _value_integerIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_value_integerIsValid;         
        if (oldValue !== value)
        {
            model_internal::_value_integerIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "value_integerIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get value_integerIsValid():Boolean
    {
        if (!model_internal::_value_integerIsValidCacheInitialized)
        {
            model_internal::calculateValue_integerIsValid();
        }

        return model_internal::_value_integerIsValid;
    }

    model_internal function calculateValue_integerIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_value_integerValidator.validate(model_internal::_instance.value_integer)
        model_internal::_value_integerIsValid_der = (valRes.results == null);
        model_internal::_value_integerIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::value_integerValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::value_integerValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get value_integerValidationFailureMessages():Array
    {
        if (model_internal::_value_integerValidationFailureMessages == null)
            model_internal::calculateValue_integerIsValid();

        return _value_integerValidationFailureMessages;
    }

    model_internal function set value_integerValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_value_integerValidationFailureMessages;

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
            model_internal::_value_integerValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "value_integerValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get unitStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get unitValidator() : StyleValidator
    {
        return model_internal::_unitValidator;
    }

    model_internal function set _unitIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_unitIsValid;         
        if (oldValue !== value)
        {
            model_internal::_unitIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "unitIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get unitIsValid():Boolean
    {
        if (!model_internal::_unitIsValidCacheInitialized)
        {
            model_internal::calculateUnitIsValid();
        }

        return model_internal::_unitIsValid;
    }

    model_internal function calculateUnitIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_unitValidator.validate(model_internal::_instance.unit)
        model_internal::_unitIsValid_der = (valRes.results == null);
        model_internal::_unitIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::unitValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::unitValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get unitValidationFailureMessages():Array
    {
        if (model_internal::_unitValidationFailureMessages == null)
            model_internal::calculateUnitIsValid();

        return _unitValidationFailureMessages;
    }

    model_internal function set unitValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_unitValidationFailureMessages;

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
            model_internal::_unitValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "unitValidationFailureMessages", oldValue, value));
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
            case("value_integer"):
            {
                return value_integerValidationFailureMessages;
            }
            case("unit"):
            {
                return unitValidationFailureMessages;
            }
            default:
            {
                return emptyArray;
            }
         }
     }

}

}
