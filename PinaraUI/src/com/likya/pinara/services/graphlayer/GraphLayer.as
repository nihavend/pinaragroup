/**
 * This is a generated sub-class of _GraphLayer.as and is intended for behavior
 * customization.  This class is only generated when there is no file already present
 * at its target location.  Thus custom behavior that you add here will survive regeneration
 * of the super-class. 
 **/
 
package com.likya.pinara.services.graphlayer
{

public class GraphLayer extends _Super_GraphLayer
{
    /**
     * Override super.init() to provide any initialization customization if needed.
     */
    protected override function preInitializeService():void
    {
		_serviceControl.baseURL = null;
        super.preInitializeService();
        // Initialization customization goes here
    }
               
}

}
