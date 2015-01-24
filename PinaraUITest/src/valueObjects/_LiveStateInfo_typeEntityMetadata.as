
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
internal class _LiveStateInfo_typeEntityMetadata extends com.adobe.fiber.valueobjects.AbstractEntityMetadata
{
    private static var emptyArray:Array = new Array();

    model_internal static var allProperties:Array = new Array("LSIDateTime", "StateName", "SubstateName", "StatusName");
    model_internal static var allAssociationProperties:Array = new Array();
    model_internal static var allRequiredProperties:Array = new Array("LSIDateTime", "StateName", "SubstateName", "StatusName");
    model_internal static var allAlwaysAvailableProperties:Array = new Array("LSIDateTime", "StateName", "SubstateName", "StatusName");
    model_internal static var guardedProperties:Array = new Array();
    model_internal static var dataProperties:Array = new Array("LSIDateTime", "StateName", "SubstateName", "StatusName");
    model_internal static var sourceProperties:Array = emptyArray
    model_internal static var nonDerivedProperties:Array = new Array("LSIDateTime", "StateName", "SubstateName", "StatusName");
    model_internal static var derivedProperties:Array = new Array();
    model_internal static var collectionProperties:Array = new Array();
    model_internal static var collectionBaseMap:Object;
    model_internal static var entityName:String = "LiveStateInfo_type";
    model_internal static var dependentsOnMap:Object;
    model_internal static var dependedOnServices:Array = new Array();
    model_internal static var propertyTypeMap:Object;

    
    model_internal var _LSIDateTimeIsValid:Boolean;
    model_internal var _LSIDateTimeValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _LSIDateTimeIsValidCacheInitialized:Boolean = false;
    model_internal var _LSIDateTimeValidationFailureMessages:Array;
    
    model_internal var _StateNameIsValid:Boolean;
    model_internal var _StateNameValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _StateNameIsValidCacheInitialized:Boolean = false;
    model_internal var _StateNameValidationFailureMessages:Array;
    
    model_internal var _SubstateNameIsValid:Boolean;
    model_internal var _SubstateNameValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _SubstateNameIsValidCacheInitialized:Boolean = false;
    model_internal var _SubstateNameValidationFailureMessages:Array;
    
    model_internal var _StatusNameIsValid:Boolean;
    model_internal var _StatusNameValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _StatusNameIsValidCacheInitialized:Boolean = false;
    model_internal var _StatusNameValidationFailureMessages:Array;

    model_internal var _instance:_Super_LiveStateInfo_type;
    model_internal static var _nullStyle:com.adobe.fiber.styles.Style = new com.adobe.fiber.styles.Style();

    public function _LiveStateInfo_typeEntityMetadata(value : _Super_LiveStateInfo_type)
    {
        // initialize property maps
        if (model_internal::dependentsOnMap == null)
        {
            // dependents map
            model_internal::dependentsOnMap = new Object();
            model_internal::dependentsOnMap["LSIDateTime"] = new Array();
            model_internal::dependentsOnMap["StateName"] = new Array();
            model_internal::dependentsOnMap["SubstateName"] = new Array();
            model_internal::dependentsOnMap["StatusName"] = new Array();

            // collection base map
            model_internal::collectionBaseMap = new Object();
        }

        // Property type Map
        model_internal::propertyTypeMap = new Object();
        model_internal::propertyTypeMap["LSIDateTime"] = "String";
        model_internal::propertyTypeMap["StateName"] = "String";
        model_internal::propertyTypeMap["SubstateName"] = "String";
        model_internal::propertyTypeMap["StatusName"] = "String";

        model_internal::_instance = value;
        model_internal::_LSIDateTimeValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForLSIDateTime);
        model_internal::_LSIDateTimeValidator.required = true;
        model_internal::_LSIDateTimeValidator.requiredFieldError = "LSIDateTime is required";
        //model_internal::_LSIDateTimeValidator.source = model_internal::_instance;
        //model_internal::_LSIDateTimeValidator.property = "LSIDateTime";
        model_internal::_StateNameValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForStateName);
        model_internal::_StateNameValidator.required = true;
        model_internal::_StateNameValidator.requiredFieldError = "StateName is required";
        //model_internal::_StateNameValidator.source = model_internal::_instance;
        //model_internal::_StateNameValidator.property = "StateName";
        model_internal::_SubstateNameValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForSubstateName);
        model_internal::_SubstateNameValidator.required = true;
        model_internal::_SubstateNameValidator.requiredFieldError = "SubstateName is required";
        //model_internal::_SubstateNameValidator.source = model_internal::_instance;
        //model_internal::_SubstateNameValidator.property = "SubstateName";
        model_internal::_StatusNameValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForStatusName);
        model_internal::_StatusNameValidator.required = true;
        model_internal::_StatusNameValidator.requiredFieldError = "StatusName is required";
        //model_internal::_StatusNameValidator.source = model_internal::_instance;
        //model_internal::_StatusNameValidator.property = "StatusName";
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
            throw new Error(propertyName + " is not a data property of entity LiveStateInfo_type");
            
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
            throw new Error(propertyName + " is not a collection property of entity LiveStateInfo_type");

        return model_internal::collectionBaseMap[propertyName];
    }
    
    override public function getPropertyType(propertyName:String):String
    {
        if (model_internal::allProperties.indexOf(propertyName) == -1)
            throw new Error(propertyName + " is not a property of LiveStateInfo_type");

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
            throw new Error(propertyName + " does not exist for entity LiveStateInfo_type");
        }

        return model_internal::_instance[propertyName];
    }

    override public function setValue(propertyName:String, value:*):void
    {
        if (model_internal::nonDerivedProperties.indexOf(propertyName) == -1)
        {
            throw new Error(propertyName + " is not a modifiable property of entity LiveStateInfo_type");
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
            throw new Error(propertyName + " does not exist for entity LiveStateInfo_type");
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
    public function get isLSIDateTimeAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isStateNameAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isSubstateNameAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isStatusNameAvailable():Boolean
    {
        return true;
    }


    /**
     * derived property recalculation
     */
    public function invalidateDependentOnLSIDateTime():void
    {
        if (model_internal::_LSIDateTimeIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfLSIDateTime = null;
            model_internal::calculateLSIDateTimeIsValid();
        }
    }
    public function invalidateDependentOnStateName():void
    {
        if (model_internal::_StateNameIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfStateName = null;
            model_internal::calculateStateNameIsValid();
        }
    }
    public function invalidateDependentOnSubstateName():void
    {
        if (model_internal::_SubstateNameIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfSubstateName = null;
            model_internal::calculateSubstateNameIsValid();
        }
    }
    public function invalidateDependentOnStatusName():void
    {
        if (model_internal::_StatusNameIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfStatusName = null;
            model_internal::calculateStatusNameIsValid();
        }
    }

    model_internal function fireChangeEvent(propertyName:String, oldValue:Object, newValue:Object):void
    {
        this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, propertyName, oldValue, newValue));
    }

    [Bindable(event="propertyChange")]   
    public function get LSIDateTimeStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get LSIDateTimeValidator() : StyleValidator
    {
        return model_internal::_LSIDateTimeValidator;
    }

    model_internal function set _LSIDateTimeIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_LSIDateTimeIsValid;         
        if (oldValue !== value)
        {
            model_internal::_LSIDateTimeIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "LSIDateTimeIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get LSIDateTimeIsValid():Boolean
    {
        if (!model_internal::_LSIDateTimeIsValidCacheInitialized)
        {
            model_internal::calculateLSIDateTimeIsValid();
        }

        return model_internal::_LSIDateTimeIsValid;
    }

    model_internal function calculateLSIDateTimeIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_LSIDateTimeValidator.validate(model_internal::_instance.LSIDateTime)
        model_internal::_LSIDateTimeIsValid_der = (valRes.results == null);
        model_internal::_LSIDateTimeIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::LSIDateTimeValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::LSIDateTimeValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get LSIDateTimeValidationFailureMessages():Array
    {
        if (model_internal::_LSIDateTimeValidationFailureMessages == null)
            model_internal::calculateLSIDateTimeIsValid();

        return _LSIDateTimeValidationFailureMessages;
    }

    model_internal function set LSIDateTimeValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_LSIDateTimeValidationFailureMessages;

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
            model_internal::_LSIDateTimeValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "LSIDateTimeValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get StateNameStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get StateNameValidator() : StyleValidator
    {
        return model_internal::_StateNameValidator;
    }

    model_internal function set _StateNameIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_StateNameIsValid;         
        if (oldValue !== value)
        {
            model_internal::_StateNameIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "StateNameIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get StateNameIsValid():Boolean
    {
        if (!model_internal::_StateNameIsValidCacheInitialized)
        {
            model_internal::calculateStateNameIsValid();
        }

        return model_internal::_StateNameIsValid;
    }

    model_internal function calculateStateNameIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_StateNameValidator.validate(model_internal::_instance.StateName)
        model_internal::_StateNameIsValid_der = (valRes.results == null);
        model_internal::_StateNameIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::StateNameValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::StateNameValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get StateNameValidationFailureMessages():Array
    {
        if (model_internal::_StateNameValidationFailureMessages == null)
            model_internal::calculateStateNameIsValid();

        return _StateNameValidationFailureMessages;
    }

    model_internal function set StateNameValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_StateNameValidationFailureMessages;

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
            model_internal::_StateNameValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "StateNameValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get SubstateNameStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get SubstateNameValidator() : StyleValidator
    {
        return model_internal::_SubstateNameValidator;
    }

    model_internal function set _SubstateNameIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_SubstateNameIsValid;         
        if (oldValue !== value)
        {
            model_internal::_SubstateNameIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "SubstateNameIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get SubstateNameIsValid():Boolean
    {
        if (!model_internal::_SubstateNameIsValidCacheInitialized)
        {
            model_internal::calculateSubstateNameIsValid();
        }

        return model_internal::_SubstateNameIsValid;
    }

    model_internal function calculateSubstateNameIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_SubstateNameValidator.validate(model_internal::_instance.SubstateName)
        model_internal::_SubstateNameIsValid_der = (valRes.results == null);
        model_internal::_SubstateNameIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::SubstateNameValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::SubstateNameValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get SubstateNameValidationFailureMessages():Array
    {
        if (model_internal::_SubstateNameValidationFailureMessages == null)
            model_internal::calculateSubstateNameIsValid();

        return _SubstateNameValidationFailureMessages;
    }

    model_internal function set SubstateNameValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_SubstateNameValidationFailureMessages;

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
            model_internal::_SubstateNameValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "SubstateNameValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get StatusNameStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get StatusNameValidator() : StyleValidator
    {
        return model_internal::_StatusNameValidator;
    }

    model_internal function set _StatusNameIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_StatusNameIsValid;         
        if (oldValue !== value)
        {
            model_internal::_StatusNameIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "StatusNameIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get StatusNameIsValid():Boolean
    {
        if (!model_internal::_StatusNameIsValidCacheInitialized)
        {
            model_internal::calculateStatusNameIsValid();
        }

        return model_internal::_StatusNameIsValid;
    }

    model_internal function calculateStatusNameIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_StatusNameValidator.validate(model_internal::_instance.StatusName)
        model_internal::_StatusNameIsValid_der = (valRes.results == null);
        model_internal::_StatusNameIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::StatusNameValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::StatusNameValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get StatusNameValidationFailureMessages():Array
    {
        if (model_internal::_StatusNameValidationFailureMessages == null)
            model_internal::calculateStatusNameIsValid();

        return _StatusNameValidationFailureMessages;
    }

    model_internal function set StatusNameValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_StatusNameValidationFailureMessages;

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
            model_internal::_StatusNameValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "StatusNameValidationFailureMessages", oldValue, value));
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
            case("LSIDateTime"):
            {
                return LSIDateTimeValidationFailureMessages;
            }
            case("StateName"):
            {
                return StateNameValidationFailureMessages;
            }
            case("SubstateName"):
            {
                return SubstateNameValidationFailureMessages;
            }
            case("StatusName"):
            {
                return StatusNameValidationFailureMessages;
            }
            default:
            {
                return emptyArray;
            }
         }
     }

}

}
