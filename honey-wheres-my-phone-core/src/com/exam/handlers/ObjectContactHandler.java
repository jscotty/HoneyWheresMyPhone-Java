package com.exam.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ObjectContactHandler implements ContactListener {

	/* called when collided
	 */
	@Override
	public void beginContact(Contact contact) {
		
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
