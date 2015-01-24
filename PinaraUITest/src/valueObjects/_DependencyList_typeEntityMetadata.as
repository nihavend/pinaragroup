
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
import valueObjects.Item_type;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IModelType;
import mx.events.PropertyChangeEvent;

use namespace model_internal;

[ExcludeClass]
internal class _DependencyList_typeEntityMetadata extends com.adobe.fiber.valueobjects.AbstractEntityMetadata
{
    private static var emptyArray:Array = new Array();

    model_internal static var allProperties:Array = new Array("Item", "DependencyExpression");
    model_internal static var allAssociationProperties:Array = new Array();
    model_internal static var allRequiredProperties:Array = new Array("Item", "DependencyExpression");
    model_internal static var allAlwaysAvailableProperties:Array = new Array("Item", "DependencyExpression");
    model_internal static var guardedProperties:Array = new Array();
    model_internal static var dataProperties:Array = new Array("Item", "DependencyExpression");
    model_internal static var sourceProperties:Array = emptyArray
    model_internal static var nonDerivedProperties:Array = new Array("Item", "DependencyExpression");
    model_internal static var derivedProperties:Array = new Array();
    model_internal static var collectionProperties:Array = new Array();
    model_internal static var collectionBaseMap:Object;
    model_internal static var entityName:String = "DependencyList_type";
    model_internal static var dependentsOnMap:Object;
    model_internal static var dependedOnServices:Array = new Array();
    model_internal static var propertyTypeMap:Object;

    
    model_internal var _ItemIsValid:Boolean;
    model_internal var _ItemValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _ItemIsValidCacheInitialized:Boolean = false;
    model_internal var _ItemValidationFailureMessages:Array;
    
    model_internal var _DependencyExpressionIsValid:Boolean;
    model_internal var _DependencyExpressionValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _DependencyExpressionIsValidCacheInitialized:Boolean = false;
    model_internal var _DependencyExpressionValidationFailureMessages:Array;

    model_internal var _instance:_Super_DependencyList_type;
    model_internal static var _nullStyle:com.adobe.fiber.styles.Style = new com.adobe.fiber.styles.Style();

    public function _DependencyList_typeEntityMetadata(value : _Super_DependencyList_type)
    {
        // initialize property maps
        if (model_internal::dependentsOnMap == null)
        {
            // dependents map
            model_internal::dependentsOnMap = new Object();
            model_internal::dependentsOnMap["Item"] = new Array();
            model_internal::dependentsOnMap["DependencyExpression"] = new Array();

            // collection base map
            model_internal::collectionBaseMap = new Object();
        }

        // Property type Map
        model_internal::propertyTypeMap = new Object();
        model_internal::propertyTypeMap["Item"] = "valueObjects.Item_type";
        model_internal::propertyTypeMap["DependencyExpression"] = "String";

        model_internal::_instance = value;
        model_internal::_ItemValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForItem);
        model_internal::_ItemValidator.required = true;
        model_internal::_ItemValidator.requiredFieldError = "Item is required";
        //model_internal::_ItemValidator.source = model_internal::_instance;
        //model_internal::_ItemValidator.property = "Item";
        model_internal::_DependencyExpressionValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForDependencyExpression);
        model_internal::_DependencyExpressionValidator.required = true;
        model_internal::_DependencyExpressionValidator.requiredFieldError = "DependencyExpression is required";
        //model_internal::_DependencyExpressionValidator.source = model_internal::_instance;
        //model_internal::_DependencyExpressionValidator.property = "DependencyExpression";
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
            throw new Error(propertyName + " is not a data property of entity DependencyList_type");
            
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
            throw new Error(propertyName + " is not a collection property of entity DependencyList_type");

        return model_internal::collectionBaseMap[propertyName];
    }
    
    override public function getPropertyType(propertyName:String):String
    {
        if (model_internal::allProperties.indexOf(propertyName) == -1)
            throw new Error(propertyName + " is not a property of DependencyList_type");

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
            throw new Error(propertyName + " does not exist for entity DependencyList_type");
        }

        return model_internal::_instance[propertyName];
    }

    override public function setValue(propertyName:String, value:*):void
    {
        if (model_internal::nonDerivedProperties.indexOf(propertyName) == -1)
        {
            throw new Error(propertyName + " is not a modifiable property of entity DependencyList_type");
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
            throw new Error(propertyName + " does not exist for entity DependencyList_type");
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
    public function get isItemAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isDependencyExpressionAvailable():Boolean
    {
        return true;
    }


    /**
     * derived property recalculation
     */
    public function invalidateDependentOnItem():void
    {
        if (model_internal::_ItemIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfItem = null;
            model_internal::calculateItemIsValid();
        }
    }
    public function invalidateDependentOnDependencyExpression():void
    {
        if (model_internal::_DependencyExpressionIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfDependencyExpression = null;
            model_internal::calculateDependencyExpressionIsValid();
        }
    }

    model_internal function fireChangeEvent(propertyName:String, oldValue:Object, newValue:Object):void
    {
        this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, propertyName, oldValue, newValue));
    }

    [Bindable(event="propertyChange")]   
    public function get ItemStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get ItemValidator() : StyleValidator
    {
        return model_internal::_ItemValidator;
    }

    model_internal function set _ItemIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_ItemIsValid;         
        if (oldValue !== value)
        {
            model_internal::_ItemIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "ItemIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get ItemIsValid():Boolean
    {
        if (!model_internal::_ItemIsValidCacheInitialized)
        {
            model_internal::calculateItemIsValid();
        }

        return model_internal::_ItemIsValid;
    }

    model_internal function calculateItemIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_ItemValidator.validate(model_internal::_instance.Item)
        model_internal::_ItemIsValid_der = (valRes.results == null);
        model_internal::_ItemIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::ItemValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::ItemValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get ItemValidationFailureMessages():Array
    {
        if (model_internal::_ItemValidationFailureMessages == null)
            model_internal::calculateItemIsValid();

        return _ItemValidationFailureMessages;
    }

    model_internal function set ItemValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_ItemValidationFailureMessages;

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
            model_internal::_ItemValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "ItemValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get DependencyExpressionStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get DependencyExpressionValidator() : StyleValidator
    {
        return model_internal::_DependencyExpressionValidator;
    }

    model_internal function set _DependencyExpressionIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_DependencyExpressionIsValid;         
        if (oldValue !== value)
        {
            model_internal::_DependencyExpressionIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "DependencyExpressionIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get DependencyExpressionIsValid():Boolean
    {
        if (!model_internal::_DependencyExpressionIsValidCacheInitialized)
        {
            model_internal::calculateDependencyExpressionIsValid();
        }

        return model_internal::_DependencyExpressionIsValid;
    }

    model_internal function calculateDependencyExpressionIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_DependencyExpressionValidator.validate(model_internal::_instance.DependencyExpression)
        model_internal::_DependencyExpressionIsValid_der = (valRes.results == null);
        model_internal::_DependencyExpressionIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::DependencyExpressionValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::DependencyExpressionValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get DependencyExpressionValidationFailureMessages():Array
    {
        if (model_internal::_DependencyExpressionValidationFailureMessages == null)
            model_internal::calculateDependencyExpressionIsValid();

        return _DependencyExpressionValidationFailureMessages;
    }

    model_internal function set DependencyExpressionValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_DependencyExpressionValidationFailureMessages;

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
            model_internal::_DependencyExpressionValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "DependencyExpressionValidationFailureMessages", oldValue, value));
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
            case("Item"):
            {
                return ItemValidationFailureMessages;
            }
            case("DependencyExpression"):
            {
                return DependencyExpressionValidationFailureMessages;
            }
            default:
            {
                return emptyArray;
            }
         }
     }

}

}
