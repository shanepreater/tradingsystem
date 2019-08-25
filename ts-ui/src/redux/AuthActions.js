import {ACCESS_TOKEN} from "../constants";
import {LOGIN_ERROR, LOGIN_SUCCESS, LOGOUT_SUCCESS, SIGNUP_ERROR, SIGNUP_SUCCESS} from './Types'
import {getCurrentUser, loginRequest, signup, signupRequest} from "../service/UserService";

export const signInError = (errorMessage) => {
    return {
        type: LOGIN_ERROR,
        errorMessage
    };
};

export const logIn = (request) => {
    return dispatch => {
        loginRequest(request).then(response => {
            const token = response.accessToken;
            updateCurrentUser(token)(dispatch);
        }).catch(errorMessage => {
            console.log(`Error logging in: ${errorMessage}`);
            dispatch({
                type: LOGIN_ERROR,
                errorMessage
            });
        })
    };
};

export const tokenReceived = (token) => {
    console.log("Token received");
    return dispatch => {
        updateCurrentUser(token)(dispatch);
    }
};

export const updateCurrentUser = (token) => {
    return (dispatch) => {
        console.log("Updating the current user");
        console.log(token);
        localStorage.setItem(ACCESS_TOKEN, token);
        getCurrentUser().then(response => {
            console.log("User has logging in");
            console.log(response);
            dispatch({
                type: LOGIN_SUCCESS,
                user: response,
                token: token
            });
        }).catch(errorMessage => {
            dispatch({
                type: LOGIN_ERROR,
                errorMessage
            })
        })
    }
};

export const signOut = () => {
    return dispatch => {
        localStorage.removeItem(ACCESS_TOKEN);
        dispatch({
            type: LOGOUT_SUCCESS
        })
    };
};

export const signUp = (request) => {
    return dispatch => {
        signupRequest(request).then((response) => {
                const token = response.token;
                updateCurrentUser(token)(dispatch);
            }
        ).catch(errorMessage => {
            dispatch({
                type: SIGNUP_ERROR,
                errorMessage
            });
        });
    };
};