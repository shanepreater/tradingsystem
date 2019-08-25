import {DISCLAIMER_ACCEPTED} from "./Types";

const initState = {
    disclaimer: "<p>You must accept!</p><p>Resistance is futile!</p>",
    disclaimerVersion: "0.0.1.BETA",
    version: "0.0.1.SNAPSHOT",
    accepted: false,
};

const handleDisclaimerAccepted = (state, action) => {
    return {
        ...state,
        accepted: true
    }
};

const handlerMap = {
    [DISCLAIMER_ACCEPTED]: handleDisclaimerAccepted,
};

const configReducer = (state = initState, action) => {
    if (action) {
        const handler = handlerMap[action.type];
        if (handler) {
            return handler(state, action);
        }
    }
    return state;
};

export default configReducer