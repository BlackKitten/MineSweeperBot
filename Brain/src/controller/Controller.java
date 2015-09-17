package controller;

import controller.view.Interface;

public abstract class Controller {
	abstract public NNHandler_base getNN();
	abstract public Interface getInterface();
}
