import {fireAcceptDisclaimer} from "../service/UserService";
import {DISCLAIMER_ACCEPTED} from "./Types";
import {errorWithComms} from "./SystemActions";

export const disclaimerAccepted = (user, version) => {
    return (dispatch) => {
        localStorage.setItem(DISCLAIMER_ACCEPTED, version);
        fireAcceptDisclaimer(user, version)
            .then(value => dispatch({
                type: 'DISCLAIMER_ACCEPTED'
            }))
            .catch(reason => errorWithComms(reason))
    };
};