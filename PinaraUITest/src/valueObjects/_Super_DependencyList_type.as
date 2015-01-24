/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this value object you may modify the generated sub-class of this class - DependencyList_type.as.
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
import valueObjects.Item_type;

import flash.net.registerClassAlias;
import flash.net.getClassByAlias;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;

use namespace model_internal;

[ExcludeClass]
public class _Super_DependencyList_type extends flash.events.EventDispatcher implements com.adobe.fiber.valueobjects.IValueObject
{
    model_internal static function initRemoteClassAliasSingle(cz:Class) : void
    {
    }

    model_internal static function initRemoteClassAliasAllRelated() : void
    {
        valueObjects.Item_type.initRemoteClassAliasSingleChild();
        valueObjects.JsDependencyRule.initRemoteClassAliasSingleChild();
    }

    model_internal var _dminternal_model : _DependencyList_typeEntityMetadata;
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
    private var _internal_Item : valueObjects.Item_type;
    private var _internal_DependencyExpression : String;

    private static var emptyArray:Array = new Array();


    /**
     * derived property cache initialization
     */
    model_internal var _cacheInitialized_isValid:Boolean = false;

    model_internal var _changeWatcherArray:Array = new Array();

    public function _Super_DependencyList_type()
    {
        _model = new _DependencyList_typeEntityMetadata(this);

        // Bind to own data or source properties for cache invalidation triggering
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "Item", model_internal::setterListenerItem));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "DependencyExpression", model_internal::setterListenerDependencyExpression));

    }

    /**
     * data/source property getters
     */

    [Bindable(event="propertyChange")]
    public function get Item() : valueObjects.Item_type
    {
        return _internal_Item;
    }

    [Bindable(event="propertyChange")]
    public function get DependencyExpression() : String
    {
        return _internal_DependencyExpression;
    }

    public function clearAssociations() : void
    {
    }

    /**
     * data/source property setters
     */

    public function set Item(value:valueObjects.Item_type) : void
    {
        var oldValue:valueObjects.Item_type = _internal_Item;
        if (oldValue !== value)
        {
            _internal_Item = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "Item", oldValue, _internal_Item));
        }
    }

    public function set DependencyExpression(value:String) : void
    {
        var oldValue:String = _internal_DependencyExpression;
        if (oldValue !== value)
        {
            _internal_DependencyExpression = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "DependencyExpression", oldValue, _internal_DependencyExpression));
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

    model_internal function setterListenerItem(value:flash.events.Event):void
    {
        _model.invalidateDependentOnItem();
    }

    model_internal function setterListenerDependencyExpression(value:flash.events.Event):void
    {
        _model.invalidateDependentOnDependencyExpression();
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
        if (!_model.ItemIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_ItemValidationFailureMessages);
        }
        if (!_model.DependencyExpressionIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_DependencyExpressionValidationFailureMessages);
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
    public function get _model() : _DependencyList_typeEntityMetadata
    {
        return model_internal::_dminternal_model;
    }

    public function set _model(value : _DependencyList_typeEntityMetadata) : void
    {
        var oldValue : _DependencyList_typeEntityMetadata = model_internal::_dminternal_model;
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

    model_internal var _doValidationCacheOfItem : Array = null;
    model_internal var _doValidationLastValOfItem : valueObjects.Item_type;

    model_internal function _doValidationForItem(valueIn:Object):Array
    {
        var value : valueObjects.Item_type = valueIn as valueObjects.Item_type;

        if (model_internal::_doValidationCacheOfItem != null && model_internal::_doValidationLastValOfItem == value)
           return model_internal::_doValidationCacheOfItem ;

        _model.model_internal::_ItemIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isItemAvailable && _internal_Item == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "Item is required"));
        }

        model_internal::_doValidationCacheOfItem = validationFailures;
        model_internal::_doValidationLastValOfItem = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfDependencyExpression : Array = null;
    model_internal var _doValidationLastValOfDependencyExpression : String;

    model_internal function _doValidationForDependencyExpression(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfDependencyExpression != null && model_internal::_doValidationLastValOfDependencyExpression == value)
           return model_internal::_doValidationCacheOfDependencyExpression ;

        _model.model_internal::_DependencyExpressionIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isDependencyExpressionAvailable && _internal_DependencyExpression == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "DependencyExpression is required"));
        }

        model_internal::_doValidationCacheOfDependencyExpression = validationFailures;
        model_internal::_doValidationLastValOfDependencyExpression = value;

        return validationFailures;
    }
    

}

}
