import {LOGIN_ERROR, LOGIN_SUCCESS, LOGOUT_SUCCESS, SIGNUP_ERROR, SIGNUP_SUCCESS} from "./Types";

const initial_State = {
    authenticated: false,
    authError: null,
    user: null,
    token: null,
};

const handleLoginError = (state, loginAction) => {
    console.log("Error during sign in");
    state.authError = loginAction.errorMessage;
    state.authenticated = false;

    return {
        ...state,
        authError: loginAction.errorMessage,
        authenticated: false,
        token: null
    };
};

const handleLoginSuccess = (state, loginAction) => {
    console.log(`Handling the login success`)
    return {
        ...state,
        authenticated: true,
        user: loginAction.user,
        token: loginAction.token,
        authError: null
    }
};

const handleSignOutSuccess = (state) => {
    return {
        ...state,
        user: null,
        authError: null,
        token: null,
        authenticated: false
    };
};

const handleSignUpError = (state, loginAction) => {
    console.log("Error during sign up");
    return {
        ...state,
        authError: loginAction.errorMessage,
        authenticated: false,
        token: null
    };
};

const handlerMap = {
    [LOGIN_ERROR]: handleLoginError,
    [LOGIN_SUCCESS]: handleLoginSuccess,
    [LOGOUT_SUCCESS]: handleSignOutSuccess,
    [SIGNUP_SUCCESS]: handleLoginSuccess,
    [SIGNUP_ERROR]: handleSignUpError
};

const authReducer = (state = initial_State, action) => {
    if (action) {
        const handler = handlerMap[action.type];
        if (handler) {
            return handler(state, action);
        }
    }
    return state;
};

export default authReducer;