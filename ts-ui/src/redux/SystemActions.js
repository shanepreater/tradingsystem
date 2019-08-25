import {ERROR_COMMUNICATING_WITH_SERVER, TOGGLE_DRAWER} from "./Types";

export const errorWithComms = (errorMessage) => {
    return {
        type: ERROR_COMMUNICATING_WITH_SERVER,
        message: errorMessage
    }
};

export const toggleOpen = () => ({
    type: TOGGLE_DRAWER
});