package com.exam.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.exam.items.ItemManager;

public class ObjectContactListener implements ContactListener {

	/* called when collided
	 */
	@Override
	public void beginContact(Contact contact) {
		if(contact.getFixtureA().getUserData() == ItemManager.USER_DATA)
			System.out.println("A Eey");
		if(contact.getFixtureB().getUserData() == ItemManager.USER_DATA)
			System.out.println("B Eey");
		
		System.out.println(contact.getFixtureA().getUserData() + " " + contact.getFixtureB().getUserData());
	}

	/* called when collided stopped!
	 */
	@Override
	public void endContact(Contact contact) {
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}

}
