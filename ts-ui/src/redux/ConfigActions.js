import {fireAcceptDisclaimer} from "../service/UserService";
import {DISCLAIMER_ACCEPTED} from "./Types";
import {errorWithComms} from "./SystemActions";

export const disclaimerAccepted = () => {
    return (dispatch, getState, services) => {
        const user = state.auth.user
        const disclaimerVersion = state.config.disclaimerVersion
        localStorage.setItem(DISCLAIMER_ACCEPTED, version);
        fireAcceptDisclaimer(user, version)
            .then(value => dispatch({
                type: DISCLAIMER_ACCEPTED
            }))
            .catch(reason => errorWithComms(reason))
    };
};