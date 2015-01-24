
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
import valueObjects.BornedPlannedTime;
import valueObjects.JsPlannedTime;
import valueObjects.JsRealTime;
import valueObjects.JsTimeOut;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IModelType;
import mx.events.PropertyChangeEvent;

use namespace model_internal;

[ExcludeClass]
internal class _TimeManagementEntityMetadata extends com.adobe.fiber.valueobjects.AbstractEntityMetadata
{
    private static var emptyArray:Array = new Array();

    model_internal static var allProperties:Array = new Array("bornedPlannedTime", "jsPlannedTime", "jsRealTime", "jsTimeOut", "expectedDuration", "previousDuration", "realizedDuration");
    model_internal static var allAssociationProperties:Array = new Array();
    model_internal static var allRequiredProperties:Array = new Array("bornedPlannedTime", "jsPlannedTime", "jsRealTime", "jsTimeOut", "expectedDuration", "previousDuration", "realizedDuration");
    model_internal static var allAlwaysAvailableProperties:Array = new Array("bornedPlannedTime", "jsPlannedTime", "jsRealTime", "jsTimeOut", "expectedDuration", "previousDuration", "realizedDuration");
    model_internal static var guardedProperties:Array = new Array();
    model_internal static var dataProperties:Array = new Array("bornedPlannedTime", "jsPlannedTime", "jsRealTime", "jsTimeOut", "expectedDuration", "previousDuration", "realizedDuration");
    model_internal static var sourceProperties:Array = emptyArray
    model_internal static var nonDerivedProperties:Array = new Array("bornedPlannedTime", "jsPlannedTime", "jsRealTime", "jsTimeOut", "expectedDuration", "previousDuration", "realizedDuration");
    model_internal static var derivedProperties:Array = new Array();
    model_internal static var collectionProperties:Array = new Array();
    model_internal static var collectionBaseMap:Object;
    model_internal static var entityName:String = "TimeManagement";
    model_internal static var dependentsOnMap:Object;
    model_internal static var dependedOnServices:Array = new Array();
    model_internal static var propertyTypeMap:Object;

    
    model_internal var _bornedPlannedTimeIsValid:Boolean;
    model_internal var _bornedPlannedTimeValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _bornedPlannedTimeIsValidCacheInitialized:Boolean = false;
    model_internal var _bornedPlannedTimeValidationFailureMessages:Array;
    
    model_internal var _jsPlannedTimeIsValid:Boolean;
    model_internal var _jsPlannedTimeValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jsPlannedTimeIsValidCacheInitialized:Boolean = false;
    model_internal var _jsPlannedTimeValidationFailureMessages:Array;
    
    model_internal var _jsRealTimeIsValid:Boolean;
    model_internal var _jsRealTimeValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jsRealTimeIsValidCacheInitialized:Boolean = false;
    model_internal var _jsRealTimeValidationFailureMessages:Array;
    
    model_internal var _jsTimeOutIsValid:Boolean;
    model_internal var _jsTimeOutValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _jsTimeOutIsValidCacheInitialized:Boolean = false;
    model_internal var _jsTimeOutValidationFailureMessages:Array;
    
    model_internal var _expectedDurationIsValid:Boolean;
    model_internal var _expectedDurationValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _expectedDurationIsValidCacheInitialized:Boolean = false;
    model_internal var _expectedDurationValidationFailureMessages:Array;
    
    model_internal var _previousDurationIsValid:Boolean;
    model_internal var _previousDurationValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _previousDurationIsValidCacheInitialized:Boolean = false;
    model_internal var _previousDurationValidationFailureMessages:Array;
    
    model_internal var _realizedDurationIsValid:Boolean;
    model_internal var _realizedDurationValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _realizedDurationIsValidCacheInitialized:Boolean = false;
    model_internal var _realizedDurationValidationFailureMessages:Array;

    model_internal var _instance:_Super_TimeManagement;
    model_internal static var _nullStyle:com.adobe.fiber.styles.Style = new com.adobe.fiber.styles.Style();

    public function _TimeManagementEntityMetadata(value : _Super_TimeManagement)
    {
        // initialize property maps
        if (model_internal::dependentsOnMap == null)
        {
            // dependents map
            model_internal::dependentsOnMap = new Object();
            model_internal::dependentsOnMap["bornedPlannedTime"] = new Array();
            model_internal::dependentsOnMap["jsPlannedTime"] = new Array();
            model_internal::dependentsOnMap["jsRealTime"] = new Array();
            model_internal::dependentsOnMap["jsTimeOut"] = new Array();
            model_internal::dependentsOnMap["expectedDuration"] = new Array();
            model_internal::dependentsOnMap["previousDuration"] = new Array();
            model_internal::dependentsOnMap["realizedDuration"] = new Array();

            // collection base map
            model_internal::collectionBaseMap = new Object();
        }

        // Property type Map
        model_internal::propertyTypeMap = new Object();
        model_internal::propertyTypeMap["bornedPlannedTime"] = "valueObjects.BornedPlannedTime";
        model_internal::propertyTypeMap["jsPlannedTime"] = "valueObjects.JsPlannedTime";
        model_internal::propertyTypeMap["jsRealTime"] = "valueObjects.JsRealTime";
        model_internal::propertyTypeMap["jsTimeOut"] = "valueObjects.JsTimeOut";
        model_internal::propertyTypeMap["expectedDuration"] = "String";
        model_internal::propertyTypeMap["previousDuration"] = "String";
        model_internal::propertyTypeMap["realizedDuration"] = "String";

        model_internal::_instance = value;
        model_internal::_bornedPlannedTimeValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForBornedPlannedTime);
        model_internal::_bornedPlannedTimeValidator.required = true;
        model_internal::_bornedPlannedTimeValidator.requiredFieldError = "bornedPlannedTime is required";
        //model_internal::_bornedPlannedTimeValidator.source = model_internal::_instance;
        //model_internal::_bornedPlannedTimeValidator.property = "bornedPlannedTime";
        model_internal::_jsPlannedTimeValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJsPlannedTime);
        model_internal::_jsPlannedTimeValidator.required = true;
        model_internal::_jsPlannedTimeValidator.requiredFieldError = "jsPlannedTime is required";
        //model_internal::_jsPlannedTimeValidator.source = model_internal::_instance;
        //model_internal::_jsPlannedTimeValidator.property = "jsPlannedTime";
        model_internal::_jsRealTimeValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJsRealTime);
        model_internal::_jsRealTimeValidator.required = true;
        model_internal::_jsRealTimeValidator.requiredFieldError = "jsRealTime is required";
        //model_internal::_jsRealTimeValidator.source = model_internal::_instance;
        //model_internal::_jsRealTimeValidator.property = "jsRealTime";
        model_internal::_jsTimeOutValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForJsTimeOut);
        model_internal::_jsTimeOutValidator.required = true;
        model_internal::_jsTimeOutValidator.requiredFieldError = "jsTimeOut is required";
        //model_internal::_jsTimeOutValidator.source = model_internal::_instance;
        //model_internal::_jsTimeOutValidator.property = "jsTimeOut";
        model_internal::_expectedDurationValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForExpectedDuration);
        model_internal::_expectedDurationValidator.required = true;
        model_internal::_expectedDurationValidator.requiredFieldError = "expectedDuration is required";
        //model_internal::_expectedDurationValidator.source = model_internal::_instance;
        //model_internal::_expectedDurationValidator.property = "expectedDuration";
        model_internal::_previousDurationValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForPreviousDuration);
        model_internal::_previousDurationValidator.required = true;
        model_internal::_previousDurationValidator.requiredFieldError = "previousDuration is required";
        //model_internal::_previousDurationValidator.source = model_internal::_instance;
        //model_internal::_previousDurationValidator.property = "previousDuration";
        model_internal::_realizedDurationValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForRealizedDuration);
        model_internal::_realizedDurationValidator.required = true;
        model_internal::_realizedDurationValidator.requiredFieldError = "realizedDuration is required";
        //model_internal::_realizedDurationValidator.source = model_internal::_instance;
        //model_internal::_realizedDurationValidator.property = "realizedDuration";
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
            throw new Error(propertyName + " is not a data property of entity TimeManagement");
            
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
            throw new Error(propertyName + " is not a collection property of entity TimeManagement");

        return model_internal::collectionBaseMap[propertyName];
    }
    
    override public function getPropertyType(propertyName:String):String
    {
        if (model_internal::allProperties.indexOf(propertyName) == -1)
            throw new Error(propertyName + " is not a property of TimeManagement");

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
            throw new Error(propertyName + " does not exist for entity TimeManagement");
        }

        return model_internal::_instance[propertyName];
    }

    override public function setValue(propertyName:String, value:*):void
    {
        if (model_internal::nonDerivedProperties.indexOf(propertyName) == -1)
        {
            throw new Error(propertyName + " is not a modifiable property of entity TimeManagement");
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
            throw new Error(propertyName + " does not exist for entity TimeManagement");
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
    public function get isBornedPlannedTimeAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isJsPlannedTimeAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isJsRealTimeAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isJsTimeOutAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isExpectedDurationAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isPreviousDurationAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isRealizedDurationAvailable():Boolean
    {
        return true;
    }


    /**
     * derived property recalculation
     */
    public function invalidateDependentOnBornedPlannedTime():void
    {
        if (model_internal::_bornedPlannedTimeIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfBornedPlannedTime = null;
            model_internal::calculateBornedPlannedTimeIsValid();
        }
    }
    public function invalidateDependentOnJsPlannedTime():void
    {
        if (model_internal::_jsPlannedTimeIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJsPlannedTime = null;
            model_internal::calculateJsPlannedTimeIsValid();
        }
    }
    public function invalidateDependentOnJsRealTime():void
    {
        if (model_internal::_jsRealTimeIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJsRealTime = null;
            model_internal::calculateJsRealTimeIsValid();
        }
    }
    public function invalidateDependentOnJsTimeOut():void
    {
        if (model_internal::_jsTimeOutIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfJsTimeOut = null;
            model_internal::calculateJsTimeOutIsValid();
        }
    }
    public function invalidateDependentOnExpectedDuration():void
    {
        if (model_internal::_expectedDurationIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfExpectedDuration = null;
            model_internal::calculateExpectedDurationIsValid();
        }
    }
    public function invalidateDependentOnPreviousDuration():void
    {
        if (model_internal::_previousDurationIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfPreviousDuration = null;
            model_internal::calculatePreviousDurationIsValid();
        }
    }
    public function invalidateDependentOnRealizedDuration():void
    {
        if (model_internal::_realizedDurationIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfRealizedDuration = null;
            model_internal::calculateRealizedDurationIsValid();
        }
    }

    model_internal function fireChangeEvent(propertyName:String, oldValue:Object, newValue:Object):void
    {
        this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, propertyName, oldValue, newValue));
    }

    [Bindable(event="propertyChange")]   
    public function get bornedPlannedTimeStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get bornedPlannedTimeValidator() : StyleValidator
    {
        return model_internal::_bornedPlannedTimeValidator;
    }

    model_internal function set _bornedPlannedTimeIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_bornedPlannedTimeIsValid;         
        if (oldValue !== value)
        {
            model_internal::_bornedPlannedTimeIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "bornedPlannedTimeIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get bornedPlannedTimeIsValid():Boolean
    {
        if (!model_internal::_bornedPlannedTimeIsValidCacheInitialized)
        {
            model_internal::calculateBornedPlannedTimeIsValid();
        }

        return model_internal::_bornedPlannedTimeIsValid;
    }

    model_internal function calculateBornedPlannedTimeIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_bornedPlannedTimeValidator.validate(model_internal::_instance.bornedPlannedTime)
        model_internal::_bornedPlannedTimeIsValid_der = (valRes.results == null);
        model_internal::_bornedPlannedTimeIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::bornedPlannedTimeValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::bornedPlannedTimeValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get bornedPlannedTimeValidationFailureMessages():Array
    {
        if (model_internal::_bornedPlannedTimeValidationFailureMessages == null)
            model_internal::calculateBornedPlannedTimeIsValid();

        return _bornedPlannedTimeValidationFailureMessages;
    }

    model_internal function set bornedPlannedTimeValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_bornedPlannedTimeValidationFailureMessages;

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
            model_internal::_bornedPlannedTimeValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "bornedPlannedTimeValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get jsPlannedTimeStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get jsPlannedTimeValidator() : StyleValidator
    {
        return model_internal::_jsPlannedTimeValidator;
    }

    model_internal function set _jsPlannedTimeIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_jsPlannedTimeIsValid;         
        if (oldValue !== value)
        {
            model_internal::_jsPlannedTimeIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsPlannedTimeIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get jsPlannedTimeIsValid():Boolean
    {
        if (!model_internal::_jsPlannedTimeIsValidCacheInitialized)
        {
            model_internal::calculateJsPlannedTimeIsValid();
        }

        return model_internal::_jsPlannedTimeIsValid;
    }

    model_internal function calculateJsPlannedTimeIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_jsPlannedTimeValidator.validate(model_internal::_instance.jsPlannedTime)
        model_internal::_jsPlannedTimeIsValid_der = (valRes.results == null);
        model_internal::_jsPlannedTimeIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::jsPlannedTimeValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::jsPlannedTimeValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get jsPlannedTimeValidationFailureMessages():Array
    {
        if (model_internal::_jsPlannedTimeValidationFailureMessages == null)
            model_internal::calculateJsPlannedTimeIsValid();

        return _jsPlannedTimeValidationFailureMessages;
    }

    model_internal function set jsPlannedTimeValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_jsPlannedTimeValidationFailureMessages;

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
            model_internal::_jsPlannedTimeValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsPlannedTimeValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get jsRealTimeStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get jsRealTimeValidator() : StyleValidator
    {
        return model_internal::_jsRealTimeValidator;
    }

    model_internal function set _jsRealTimeIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_jsRealTimeIsValid;         
        if (oldValue !== value)
        {
            model_internal::_jsRealTimeIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsRealTimeIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get jsRealTimeIsValid():Boolean
    {
        if (!model_internal::_jsRealTimeIsValidCacheInitialized)
        {
            model_internal::calculateJsRealTimeIsValid();
        }

        return model_internal::_jsRealTimeIsValid;
    }

    model_internal function calculateJsRealTimeIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_jsRealTimeValidator.validate(model_internal::_instance.jsRealTime)
        model_internal::_jsRealTimeIsValid_der = (valRes.results == null);
        model_internal::_jsRealTimeIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::jsRealTimeValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::jsRealTimeValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get jsRealTimeValidationFailureMessages():Array
    {
        if (model_internal::_jsRealTimeValidationFailureMessages == null)
            model_internal::calculateJsRealTimeIsValid();

        return _jsRealTimeValidationFailureMessages;
    }

    model_internal function set jsRealTimeValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_jsRealTimeValidationFailureMessages;

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
            model_internal::_jsRealTimeValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsRealTimeValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get jsTimeOutStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get jsTimeOutValidator() : StyleValidator
    {
        return model_internal::_jsTimeOutValidator;
    }

    model_internal function set _jsTimeOutIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_jsTimeOutIsValid;         
        if (oldValue !== value)
        {
            model_internal::_jsTimeOutIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsTimeOutIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get jsTimeOutIsValid():Boolean
    {
        if (!model_internal::_jsTimeOutIsValidCacheInitialized)
        {
            model_internal::calculateJsTimeOutIsValid();
        }

        return model_internal::_jsTimeOutIsValid;
    }

    model_internal function calculateJsTimeOutIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_jsTimeOutValidator.validate(model_internal::_instance.jsTimeOut)
        model_internal::_jsTimeOutIsValid_der = (valRes.results == null);
        model_internal::_jsTimeOutIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::jsTimeOutValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::jsTimeOutValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get jsTimeOutValidationFailureMessages():Array
    {
        if (model_internal::_jsTimeOutValidationFailureMessages == null)
            model_internal::calculateJsTimeOutIsValid();

        return _jsTimeOutValidationFailureMessages;
    }

    model_internal function set jsTimeOutValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_jsTimeOutValidationFailureMessages;

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
            model_internal::_jsTimeOutValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsTimeOutValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get expectedDurationStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get expectedDurationValidator() : StyleValidator
    {
        return model_internal::_expectedDurationValidator;
    }

    model_internal function set _expectedDurationIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_expectedDurationIsValid;         
        if (oldValue !== value)
        {
            model_internal::_expectedDurationIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "expectedDurationIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get expectedDurationIsValid():Boolean
    {
        if (!model_internal::_expectedDurationIsValidCacheInitialized)
        {
            model_internal::calculateExpectedDurationIsValid();
        }

        return model_internal::_expectedDurationIsValid;
    }

    model_internal function calculateExpectedDurationIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_expectedDurationValidator.validate(model_internal::_instance.expectedDuration)
        model_internal::_expectedDurationIsValid_der = (valRes.results == null);
        model_internal::_expectedDurationIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::expectedDurationValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::expectedDurationValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get expectedDurationValidationFailureMessages():Array
    {
        if (model_internal::_expectedDurationValidationFailureMessages == null)
            model_internal::calculateExpectedDurationIsValid();

        return _expectedDurationValidationFailureMessages;
    }

    model_internal function set expectedDurationValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_expectedDurationValidationFailureMessages;

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
            model_internal::_expectedDurationValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "expectedDurationValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get previousDurationStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get previousDurationValidator() : StyleValidator
    {
        return model_internal::_previousDurationValidator;
    }

    model_internal function set _previousDurationIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_previousDurationIsValid;         
        if (oldValue !== value)
        {
            model_internal::_previousDurationIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "previousDurationIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get previousDurationIsValid():Boolean
    {
        if (!model_internal::_previousDurationIsValidCacheInitialized)
        {
            model_internal::calculatePreviousDurationIsValid();
        }

        return model_internal::_previousDurationIsValid;
    }

    model_internal function calculatePreviousDurationIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_previousDurationValidator.validate(model_internal::_instance.previousDuration)
        model_internal::_previousDurationIsValid_der = (valRes.results == null);
        model_internal::_previousDurationIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::previousDurationValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::previousDurationValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get previousDurationValidationFailureMessages():Array
    {
        if (model_internal::_previousDurationValidationFailureMessages == null)
            model_internal::calculatePreviousDurationIsValid();

        return _previousDurationValidationFailureMessages;
    }

    model_internal function set previousDurationValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_previousDurationValidationFailureMessages;

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
            model_internal::_previousDurationValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "previousDurationValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get realizedDurationStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get realizedDurationValidator() : StyleValidator
    {
        return model_internal::_realizedDurationValidator;
    }

    model_internal function set _realizedDurationIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_realizedDurationIsValid;         
        if (oldValue !== value)
        {
            model_internal::_realizedDurationIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "realizedDurationIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get realizedDurationIsValid():Boolean
    {
        if (!model_internal::_realizedDurationIsValidCacheInitialized)
        {
            model_internal::calculateRealizedDurationIsValid();
        }

        return model_internal::_realizedDurationIsValid;
    }

    model_internal function calculateRealizedDurationIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_realizedDurationValidator.validate(model_internal::_instance.realizedDuration)
        model_internal::_realizedDurationIsValid_der = (valRes.results == null);
        model_internal::_realizedDurationIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::realizedDurationValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::realizedDurationValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get realizedDurationValidationFailureMessages():Array
    {
        if (model_internal::_realizedDurationValidationFailureMessages == null)
            model_internal::calculateRealizedDurationIsValid();

        return _realizedDurationValidationFailureMessages;
    }

    model_internal function set realizedDurationValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_realizedDurationValidationFailureMessages;

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
            model_internal::_realizedDurationValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "realizedDurationValidationFailureMessages", oldValue, value));
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
            case("bornedPlannedTime"):
            {
                return bornedPlannedTimeValidationFailureMessages;
            }
            case("jsPlannedTime"):
            {
                return jsPlannedTimeValidationFailureMessages;
            }
            case("jsRealTime"):
            {
                return jsRealTimeValidationFailureMessages;
            }
            case("jsTimeOut"):
            {
                return jsTimeOutValidationFailureMessages;
            }
            case("expectedDuration"):
            {
                return expectedDurationValidationFailureMessages;
            }
            case("previousDuration"):
            {
                return previousDurationValidationFailureMessages;
            }
            case("realizedDuration"):
            {
                return realizedDurationValidationFailureMessages;
            }
            default:
            {
                return emptyArray;
            }
         }
     }

}

}
