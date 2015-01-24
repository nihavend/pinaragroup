/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this value object you may modify the generated sub-class of this class - Item_type.as.
 */

package valueObjects
{
import com.adobe.fiber.services.IFiberManagingService;
import com.adobe.fiber.util.FiberUtils;
import com.adobe.fiber.valueobjects.IValueObject;
import flash.events.Event;
import flash.events.EventDispatcher;
import mx.binding.utils.ChangeWatcher;
import mx.collections.ArrayCollection;
import mx.events.PropertyChangeEvent;
import mx.validators.ValidationResult;
import valueObjects.JsDependencyRule;

import flash.net.registerClassAlias;
import flash.net.getClassByAlias;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;

use namespace model_internal;

[ExcludeClass]
public class _Super_Item_type extends flash.events.EventDispatcher implements com.adobe.fiber.valueobjects.IValueObject
{
    model_internal static function initRemoteClassAliasSingle(cz:Class) : void
    {
    }

    model_internal static function initRemoteClassAliasAllRelated() : void
    {
        valueObjects.JsDependencyRule.initRemoteClassAliasSingleChild();
    }

    model_internal var _dminternal_model : _Item_typeEntityMetadata;
    model_internal var _changedObjects:mx.collections.ArrayCollection = new ArrayCollection();

    public function getChangedObjects() : Array
    {
        _changedObjects.addItemAt(this,0);
        return _changedObjects.source;
    }

    public function clearChangedObjects() : void
    {
        _changedObjects.removeAll();
    }

    /**
     * properties
     */
    private var _internal_dependencyID : String;
    private var _internal_jsName : String;
    private var _internal_jsId : String;
    private var _internal_jsType : String;
    private var _internal_comment : String;
    private var _internal_jsDependencyRule : valueObjects.JsDependencyRule;

    private static var emptyArray:Array = new Array();


    /**
     * derived property cache initialization
     */
    model_internal var _cacheInitialized_isValid:Boolean = false;

    model_internal var _changeWatcherArray:Array = new Array();

    public function _Super_Item_type()
    {
        _model = new _Item_typeEntityMetadata(this);

        // Bind to own data or source properties for cache invalidation triggering
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "dependencyID", model_internal::setterListenerDependencyID));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "jsName", model_internal::setterListenerJsName));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "jsId", model_internal::setterListenerJsId));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "jsType", model_internal::setterListenerJsType));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "comment", model_internal::setterListenerComment));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "jsDependencyRule", model_internal::setterListenerJsDependencyRule));

    }

    /**
     * data/source property getters
     */

    [Bindable(event="propertyChange")]
    public function get dependencyID() : String
    {
        return _internal_dependencyID;
    }

    [Bindable(event="propertyChange")]
    public function get jsName() : String
    {
        return _internal_jsName;
    }

    [Bindable(event="propertyChange")]
    public function get jsId() : String
    {
        return _internal_jsId;
    }

    [Bindable(event="propertyChange")]
    public function get jsType() : String
    {
        return _internal_jsType;
    }

    [Bindable(event="propertyChange")]
    public function get comment() : String
    {
        return _internal_comment;
    }

    [Bindable(event="propertyChange")]
    public function get jsDependencyRule() : valueObjects.JsDependencyRule
    {
        return _internal_jsDependencyRule;
    }

    public function clearAssociations() : void
    {
    }

    /**
     * data/source property setters
     */

    public function set dependencyID(value:String) : void
    {
        var oldValue:String = _internal_dependencyID;
        if (oldValue !== value)
        {
            _internal_dependencyID = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "dependencyID", oldValue, _internal_dependencyID));
        }
    }

    public function set jsName(value:String) : void
    {
        var oldValue:String = _internal_jsName;
        if (oldValue !== value)
        {
            _internal_jsName = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsName", oldValue, _internal_jsName));
        }
    }

    public function set jsId(value:String) : void
    {
        var oldValue:String = _internal_jsId;
        if (oldValue !== value)
        {
            _internal_jsId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsId", oldValue, _internal_jsId));
        }
    }

    public function set jsType(value:String) : void
    {
        var oldValue:String = _internal_jsType;
        if (oldValue !== value)
        {
            _internal_jsType = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsType", oldValue, _internal_jsType));
        }
    }

    public function set comment(value:String) : void
    {
        var oldValue:String = _internal_comment;
        if (oldValue !== value)
        {
            _internal_comment = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "comment", oldValue, _internal_comment));
        }
    }

    public function set jsDependencyRule(value:valueObjects.JsDependencyRule) : void
    {
        var oldValue:valueObjects.JsDependencyRule = _internal_jsDependencyRule;
        if (oldValue !== value)
        {
            _internal_jsDependencyRule = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jsDependencyRule", oldValue, _internal_jsDependencyRule));
        }
    }

    /**
     * Data/source property setter listeners
     *
     * Each data property whose value affects other properties or the validity of the entity
     * needs to invalidate all previously calculated artifacts. These include:
     *  - any derived properties or constraints that reference the given data property.
     *  - any availability guards (variant expressions) that reference the given data property.
     *  - any style validations, message tokens or guards that reference the given data property.
     *  - the validity of the property (and the containing entity) if the given data property has a length restriction.
     *  - the validity of the property (and the containing entity) if the given data property is required.
     */

    model_internal function setterListenerDependencyID(value:flash.events.Event):void
    {
        _model.invalidateDependentOnDependencyID();
    }

    model_internal function setterListenerJsName(value:flash.events.Event):void
    {
        _model.invalidateDependentOnJsName();
    }

    model_internal function setterListenerJsId(value:flash.events.Event):void
    {
        _model.invalidateDependentOnJsId();
    }

    model_internal function setterListenerJsType(value:flash.events.Event):void
    {
        _model.invalidateDependentOnJsType();
    }

    model_internal function setterListenerComment(value:flash.events.Event):void
    {
        _model.invalidateDependentOnComment();
    }

    model_internal function setterListenerJsDependencyRule(value:flash.events.Event):void
    {
        _model.invalidateDependentOnJsDependencyRule();
    }


    /**
     * valid related derived properties
     */
    model_internal var _isValid : Boolean;
    model_internal var _invalidConstraints:Array = new Array();
    model_internal var _validationFailureMessages:Array = new Array();

    /**
     * derived property calculators
     */

    /**
     * isValid calculator
     */
    model_internal function calculateIsValid():Boolean
    {
        var violatedConsts:Array = new Array();
        var validationFailureMessages:Array = new Array();

        var propertyValidity:Boolean = true;
        if (!_model.dependencyIDIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_dependencyIDValidationFailureMessages);
        }
        if (!_model.jsNameIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_jsNameValidationFailureMessages);
        }
        if (!_model.jsIdIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_jsIdValidationFailureMessages);
        }
        if (!_model.jsTypeIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_jsTypeValidationFailureMessages);
        }
        if (!_model.commentIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_commentValidationFailureMessages);
        }
        if (!_model.jsDependencyRuleIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_jsDependencyRuleValidationFailureMessages);
        }

        model_internal::_cacheInitialized_isValid = true;
        model_internal::invalidConstraints_der = violatedConsts;
        model_internal::validationFailureMessages_der = validationFailureMessages;
        return violatedConsts.length == 0 && propertyValidity;
    }

    /**
     * derived property setters
     */

    model_internal function set isValid_der(value:Boolean) : void
    {
        var oldValue:Boolean = model_internal::_isValid;
        if (oldValue !== value)
        {
            model_internal::_isValid = value;
            _model.model_internal::fireChangeEvent("isValid", oldValue, model_internal::_isValid);
        }
    }

    /**
     * derived property getters
     */

    [Transient]
    [Bindable(event="propertyChange")]
    public function get _model() : _Item_typeEntityMetadata
    {
        return model_internal::_dminternal_model;
    }

    public function set _model(value : _Item_typeEntityMetadata) : void
    {
        var oldValue : _Item_typeEntityMetadata = model_internal::_dminternal_model;
        if (oldValue !== value)
        {
            model_internal::_dminternal_model = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "_model", oldValue, model_internal::_dminternal_model));
        }
    }

    /**
     * methods
     */


    /**
     *  services
     */
    private var _managingService:com.adobe.fiber.services.IFiberManagingService;

    public function set managingService(managingService:com.adobe.fiber.services.IFiberManagingService):void
    {
        _managingService = managingService;
    }

    model_internal function set invalidConstraints_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_invalidConstraints;
        // avoid firing the event when old and new value are different empty arrays
        if (oldValue !== value && (oldValue.length > 0 || value.length > 0))
        {
            model_internal::_invalidConstraints = value;
            _model.model_internal::fireChangeEvent("invalidConstraints", oldValue, model_internal::_invalidConstraints);
        }
    }

    model_internal function set validationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_validationFailureMessages;
        // avoid firing the event when old and new value are different empty arrays
        if (oldValue !== value && (oldValue.length > 0 || value.length > 0))
        {
            model_internal::_validationFailureMessages = value;
            _model.model_internal::fireChangeEvent("validationFailureMessages", oldValue, model_internal::_validationFailureMessages);
        }
    }

    model_internal var _doValidationCacheOfDependencyID : Array = null;
    model_internal var _doValidationLastValOfDependencyID : String;

    model_internal function _doValidationForDependencyID(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfDependencyID != null && model_internal::_doValidationLastValOfDependencyID == value)
           return model_internal::_doValidationCacheOfDependencyID ;

        _model.model_internal::_dependencyIDIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isDependencyIDAvailable && _internal_dependencyID == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "dependencyID is required"));
        }

        model_internal::_doValidationCacheOfDependencyID = validationFailures;
        model_internal::_doValidationLastValOfDependencyID = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfJsName : Array = null;
    model_internal var _doValidationLastValOfJsName : String;

    model_internal function _doValidationForJsName(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfJsName != null && model_internal::_doValidationLastValOfJsName == value)
           return model_internal::_doValidationCacheOfJsName ;

        _model.model_internal::_jsNameIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isJsNameAvailable && _internal_jsName == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "jsName is required"));
        }

        model_internal::_doValidationCacheOfJsName = validationFailures;
        model_internal::_doValidationLastValOfJsName = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfJsId : Array = null;
    model_internal var _doValidationLastValOfJsId : String;

    model_internal function _doValidationForJsId(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfJsId != null && model_internal::_doValidationLastValOfJsId == value)
           return model_internal::_doValidationCacheOfJsId ;

        _model.model_internal::_jsIdIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isJsIdAvailable && _internal_jsId == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "jsId is required"));
        }

        model_internal::_doValidationCacheOfJsId = validationFailures;
        model_internal::_doValidationLastValOfJsId = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfJsType : Array = null;
    model_internal var _doValidationLastValOfJsType : String;

    model_internal function _doValidationForJsType(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfJsType != null && model_internal::_doValidationLastValOfJsType == value)
           return model_internal::_doValidationCacheOfJsType ;

        _model.model_internal::_jsTypeIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isJsTypeAvailable && _internal_jsType == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "jsType is required"));
        }

        model_internal::_doValidationCacheOfJsType = validationFailures;
        model_internal::_doValidationLastValOfJsType = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfComment : Array = null;
    model_internal var _doValidationLastValOfComment : String;

    model_internal function _doValidationForComment(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfComment != null && model_internal::_doValidationLastValOfComment == value)
           return model_internal::_doValidationCacheOfComment ;

        _model.model_internal::_commentIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isCommentAvailable && _internal_comment == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "comment is required"));
        }

        model_internal::_doValidationCacheOfComment = validationFailures;
        model_internal::_doValidationLastValOfComment = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfJsDependencyRule : Array = null;
    model_internal var _doValidationLastValOfJsDependencyRule : valueObjects.JsDependencyRule;

    model_internal function _doValidationForJsDependencyRule(valueIn:Object):Array
    {
        var value : valueObjects.JsDependencyRule = valueIn as valueObjects.JsDependencyRule;

        if (model_internal::_doValidationCacheOfJsDependencyRule != null && model_internal::_doValidationLastValOfJsDependencyRule == value)
           return model_internal::_doValidationCacheOfJsDependencyRule ;

        _model.model_internal::_jsDependencyRuleIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isJsDependencyRuleAvailable && _internal_jsDependencyRule == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "jsDependencyRule is required"));
        }

        model_internal::_doValidationCacheOfJsDependencyRule = validationFailures;
        model_internal::_doValidationLastValOfJsDependencyRule = value;

        return validationFailures;
    }
    

}

}
