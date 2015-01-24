
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
import valueObjects.JsDependencyRule;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IModelType;
import mx.events.PropertyChangeEvent;

use namespace model_internal;

[ExcludeClass]
internal class _Item_typeEntityMetadata extends com.adobe.fiber.valueobjects.AbstractEntityMetadata
{
    private static var emptyArray:Array = new Array();

    model_internal static var allProperties:Array = new Array("dependencyID", "jsName", "jsId", "jsType", "comment", "jsDependencyRule");
    model_internal static var allAssociationProperties:Array = new Array();
    model_internal static var allRequiredProperties:Array = new Array("dependencyID", "jsName", "jsId", "jsType", "comment", "jsDependencyRule");
    model_internal static var allAlwaysAvailableProperties:Array = new Array("dependencyID", "jsName", "jsId", "jsType", "comment", "jsDependencyRule");
    model_internal static var guardedProperties:Array = new Array();
    model_internal static var dataProperties:Array = new Array("dependencyID", "jsName", "jsId", "jsType", "comment", "jsDependencyRule");
    model_internal static var sourceProperties:Array = emptyArray
    model_internal static var nonDerivedProperties:Array = new Array("dependencyID", "jsName", "jsId", "jsType", "comment", "jsDependencyRule");
    model_internal static var derivedProperties:Array = new Array();
    model_internal static var collectionProperties:Array = new Array();
    model_internal static var collectionBaseMap:Object;
    model_internal static var entityName:String = "Item_type";
    model_internal static var dependentsOnMap:Object;
    model_internal static var dependedOnServices:Array = new Array();
    model_internal static var propertyTypeMap:Object;

    
    model_internal var _dependencyIDIsValid:Boolean;
    model_internal var _dependencyIDValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _dependencyIDIsValidCacheInitialized:Boolean = false;
    model_internal var _dependencyIDValidationFailureMessages:Array;
    
    model_internal var _jsNameIsValid:Boolean;
    model_internal var _jsNameValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jsNameIsValidCacheInitialized:Boolean = false;
    model_internal var _jsNameValidationFailureMessages:Array;
    
    model_internal var _jsIdIsValid:Boolean;
    model_internal var _jsIdValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jsIdIsValidCacheInitialized:Boolean = false;
    model_internal var _jsIdValidationFailureMessages:Array;
    
    model_internal var _jsTypeIsValid:Boolean;
    model_internal var _jsTypeValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jsTypeIsValidCacheInitialized:Boolean = false;
    model_internal var _jsTypeValidationFailureMessages:Array;
    
    model_internal var _commentIsValid:Boolean;
    model_internal var _commentValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _commentIsValidCacheInitialized:Boolean = false;
    model_internal var _commentValidationFailureMessages:Array;
    
    model_internal var _jsDependencyRuleIsValid:Boolean;
    model_internal var _jsDependencyRuleValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jsDependencyRuleIsValidCacheInitialized:Boolean = false;
    model_internal var _jsDependencyRuleValidationFailureMessages:Array;

    model_internal var _instance:_Super_Item_type;
    model_internal static var _nullStyle:com.adobe.fiber.styles.Style = new com.adobe.fiber.styles.Style();

    public function _Item_typeEntityMetadata(value : _Super_Item_type)
    {
        // initialize property maps
        if (model_internal::dependentsOnMap == null)
        {
            // dependents map
            model_internal::dependentsOnMap = new Object();
            model_internal::dependentsOnMap["dependencyID"] = new Array();
            model_internal::dependentsOnMap["jsName"] = new Array();
            model_internal::dependentsOnMap["jsId"] = new Array();
            model_internal::dependentsOnMap["jsType"] = new Array();
            model_internal::dependentsOnMap["comment"] = new Array();
            model_internal::dependentsOnMap["jsDependencyRule"] = new Array();

            // collection base map
            model_internal::collectionBaseMap = new Object();
        }

        // Property type Map
        model_internal::propertyTypeMap = new Object();
        model_internal::propertyTypeMap["dependencyID"] = "String";
        model_internal::propertyTypeMap["jsName"] = "String";
        model_internal::propertyTypeMap["jsId"] = "String";
        model_internal::propertyTypeMap["jsType"] = "String";
        model_internal::propertyTypeMap["comment"] = "String";
        model_internal::propertyTypeMap["jsDependencyRule"] = "valueObjects.JsDependencyRule";

        model_internal::_instance = value;
        model_internal::_dependencyIDValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForDependencyID);
        model_internal::_dependencyIDValidator.required = true;
        model_internal::_dependencyIDValidator.requiredFieldError = "dependencyID is required";
        //model_internal::_dependencyIDValidator.source = model_internal::_instance;
        //model_internal::_dependencyIDValidator.property = "dependencyID";
        model_internal::_jsNameValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJsName);
        model_internal::_jsNameValidator.required = true;
        model_internal::_jsNameValidator.requiredFieldError = "jsName is required";
        //model_internal::_jsNameValidator.source = model_internal::_instance;
        //model_internal::_jsNameValidator.property = "jsName";
        model_internal::_jsIdValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJsId);
        model_internal::_jsIdValidator.required = true;
        model_internal::_jsIdValidator.requiredFieldError = "jsId is required";
        //model_internal::_jsIdValidator.source = model_internal::_instance;
        //model_internal::_jsIdValidator.property = "jsId";
        model_internal::_jsTypeValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJsType);
        model_internal::_jsTypeValidator.required = true;
        model_internal::_jsTypeValidator.requiredFieldError = "jsType is required";
        //model_internal::_jsTypeValidator.source = model_internal::_instance;
        //model_internal::_jsTypeValidator.property = "jsType";
        model_internal::_commentValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForComment);
        model_internal::_commentValidator.required = true;
        model_internal::_commentValidator.requiredFieldError = "comment is required";
        //model_internal::_commentValidator.source = model_internal::_instance;
        //model_internal::_commentValidator.property = "comment";
        model_internal::_jsDependencyRuleValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJsDependencyRule);
        model_internal::_jsDependencyRuleValidator.required = true;
        model_internal::_jsDependencyRuleValidator.requiredFieldError = "jsDependencyRule is required";
        //model_internal::_jsDependencyRuleValidator.source = model_internal::_instance;
        //model_internal::_jsDependencyRuleValidator.property = "jsDependencyRule";
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
            throw new Error(propertyName + " is not a data property of entity Item_type");
            
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
            throw new Error(propertyName + " is not a collection property of entity Item_type");

        return model_internal::collectionBaseMap[propertyName];
    }
    
    override public function getPropertyType(propertyName:String):String
    {
        if (model_internal::allProperties.indexOf(propertyName) == -1)
            throw new Error(propertyName + " is not a property of Item_type");

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
            throw new Error(propertyName + " does not exist for entity Item_type");
        }

        return model_internal::_instance[propertyName];
    }

    override public function setValue(propertyName:String, value:*):void
    {
        if (model_internal::nonDerivedProperties.indexOf(propertyName) == -1)
        {
            throw new Error(propertyName + " is not a modifiable property of entity Item_type");
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
            throw new Error(propertyName + " does not exist for entity Item_type");
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
    public function get isDependencyIDAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isJsNameAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isJsIdAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isJsTypeAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isCommentAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isJsDependencyRuleAvailable():Boolean
    {
        return true;
    }


    /**
     * derived property recalculation
     */
    public function invalidateDependentOnDependencyID():void
    {
        if (model_internal::_dependencyIDIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfDependencyID = null;
            model_internal::calculateDependencyIDIsValid();
        }
    }
    public function invalidateDependentOnJsName():void
    {
        if (model_internal::_jsNameIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJsName = null;
            model_internal::calculateJsNameIsValid();
        }
    }
    public function invalidateDependentOnJsId():void
    {
        if (model_internal::_jsIdIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJsId = null;
            model_internal::calculateJsIdIsValid();
        }
    }
    public function invalidateDependentOnJsType():void
    {
        if (model_internal::_jsTypeIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJsType = null;
            model_internal::calculateJsTypeIsValid();
        }
    }
    public function invalidateDependentOnComment():void
    {
        if (model_internal::_commentIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfComment = null;
            model_internal::calculateCommentIsValid();
        }
    }
    public function invalidateDependentOnJsDependencyRule():void
    {
        if (model_internal::_jsDependencyRuleIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJsDependencyRule = null;
            model_internal::calculateJsDependencyRuleIsValid();
        }
    }

    model_internal function fireChangeEvent(propertyName:String, oldValue:Object, newValue:Object):void
    {
        this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, propertyName, oldValue, newValue));
    }

    [Bindable(event="propertyChange")]   
    public function get dependencyIDStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get dependencyIDValidator() : StyleValidator
    {
        return model_internal::_dependencyIDValidator;
    }

    model_internal function set _dependencyIDIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_dependencyIDIsValid;         
        if (oldValue !== value)
        {
            model_internal::_dependencyIDIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "dependencyIDIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get dependencyIDIsValid():Boolean
    {
        if (!model_internal::_dependencyIDIsValidCacheInitialized)
        {
            model_internal::calculateDependencyIDIsValid();
        }

        return model_internal::_dependencyIDIsValid;
    }

    model_internal function calculateDependencyIDIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_dependencyIDValidator.validate(model_internal::_instance.dependencyID)
        model_internal::_dependencyIDIsValid_der = (valRes.results == null);
        model_internal::_dependencyIDIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::dependencyIDValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::dependencyIDValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get dependencyIDValidationFailureMessages():Array
    {
        if (model_internal::_dependencyIDValidationFailureMessages == null)
            model_internal::calculateDependencyIDIsValid();

        return _dependencyIDValidationFailureMessages;
    }

    model_internal function set dependencyIDValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_dependencyIDValidationFailureMessages;

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
            model_internal::_dependencyIDValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "dependencyIDValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
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
    public function get jsIdStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get jsIdValidator() : StyleValidator
    {
        return model_internal::_jsIdValidator;
    }

    model_internal function set _jsIdIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_jsIdIsValid;         
        if (oldValue !== value)
        {
            model_internal::_jsIdIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsIdIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get jsIdIsValid():Boolean
    {
        if (!model_internal::_jsIdIsValidCacheInitialized)
        {
            model_internal::calculateJsIdIsValid();
        }

        return model_internal::_jsIdIsValid;
    }

    model_internal function calculateJsIdIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_jsIdValidator.validate(model_internal::_instance.jsId)
        model_internal::_jsIdIsValid_der = (valRes.results == null);
        model_internal::_jsIdIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::jsIdValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::jsIdValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get jsIdValidationFailureMessages():Array
    {
        if (model_internal::_jsIdValidationFailureMessages == null)
            model_internal::calculateJsIdIsValid();

        return _jsIdValidationFailureMessages;
    }

    model_internal function set jsIdValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_jsIdValidationFailureMessages;

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
            model_internal::_jsIdValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsIdValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get jsTypeStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get jsTypeValidator() : StyleValidator
    {
        return model_internal::_jsTypeValidator;
    }

    model_internal function set _jsTypeIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_jsTypeIsValid;         
        if (oldValue !== value)
        {
            model_internal::_jsTypeIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsTypeIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get jsTypeIsValid():Boolean
    {
        if (!model_internal::_jsTypeIsValidCacheInitialized)
        {
            model_internal::calculateJsTypeIsValid();
        }

        return model_internal::_jsTypeIsValid;
    }

    model_internal function calculateJsTypeIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_jsTypeValidator.validate(model_internal::_instance.jsType)
        model_internal::_jsTypeIsValid_der = (valRes.results == null);
        model_internal::_jsTypeIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::jsTypeValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::jsTypeValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get jsTypeValidationFailureMessages():Array
    {
        if (model_internal::_jsTypeValidationFailureMessages == null)
            model_internal::calculateJsTypeIsValid();

        return _jsTypeValidationFailureMessages;
    }

    model_internal function set jsTypeValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_jsTypeValidationFailureMessages;

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
            model_internal::_jsTypeValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsTypeValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get commentStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get commentValidator() : StyleValidator
    {
        return model_internal::_commentValidator;
    }

    model_internal function set _commentIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_commentIsValid;         
        if (oldValue !== value)
        {
            model_internal::_commentIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "commentIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get commentIsValid():Boolean
    {
        if (!model_internal::_commentIsValidCacheInitialized)
        {
            model_internal::calculateCommentIsValid();
        }

        return model_internal::_commentIsValid;
    }

    model_internal function calculateCommentIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_commentValidator.validate(model_internal::_instance.comment)
        model_internal::_commentIsValid_der = (valRes.results == null);
        model_internal::_commentIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::commentValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::commentValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get commentValidationFailureMessages():Array
    {
        if (model_internal::_commentValidationFailureMessages == null)
            model_internal::calculateCommentIsValid();

        return _commentValidationFailureMessages;
    }

    model_internal function set commentValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_commentValidationFailureMessages;

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
            model_internal::_commentValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "commentValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get jsDependencyRuleStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get jsDependencyRuleValidator() : StyleValidator
    {
        return model_internal::_jsDependencyRuleValidator;
    }

    model_internal function set _jsDependencyRuleIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_jsDependencyRuleIsValid;         
        if (oldValue !== value)
        {
            model_internal::_jsDependencyRuleIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsDependencyRuleIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get jsDependencyRuleIsValid():Boolean
    {
        if (!model_internal::_jsDependencyRuleIsValidCacheInitialized)
        {
            model_internal::calculateJsDependencyRuleIsValid();
        }

        return model_internal::_jsDependencyRuleIsValid;
    }

    model_internal function calculateJsDependencyRuleIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_jsDependencyRuleValidator.validate(model_internal::_instance.jsDependencyRule)
        model_internal::_jsDependencyRuleIsValid_der = (valRes.results == null);
        model_internal::_jsDependencyRuleIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::jsDependencyRuleValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::jsDependencyRuleValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get jsDependencyRuleValidationFailureMessages():Array
    {
        if (model_internal::_jsDependencyRuleValidationFailureMessages == null)
            model_internal::calculateJsDependencyRuleIsValid();

        return _jsDependencyRuleValidationFailureMessages;
    }

    model_internal function set jsDependencyRuleValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_jsDependencyRuleValidationFailureMessages;

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
            model_internal::_jsDependencyRuleValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsDependencyRuleValidationFailureMessages", oldValue, value));
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
            case("dependencyID"):
            {
                return dependencyIDValidationFailureMessages;
            }
            case("jsName"):
            {
                return jsNameValidationFailureMessages;
            }
            case("jsId"):
            {
                return jsIdValidationFailureMessages;
            }
            case("jsType"):
            {
                return jsTypeValidationFailureMessages;
            }
            case("comment"):
            {
                return commentValidationFailureMessages;
            }
            case("jsDependencyRule"):
            {
                return jsDependencyRuleValidationFailureMessages;
            }
            default:
            {
                return emptyArray;
            }
         }
     }

}

}
