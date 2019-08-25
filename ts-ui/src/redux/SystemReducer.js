import {ERROR_COMMUNICATING_WITH_SERVER, TOGGLE_DRAWER} from "./Types";

const initState = {
    latestError: null,
    previousErrors: [],
    maxErrors: 10,
    drawerOpen: false
};

const handleCommsError = (state, errorAction) => {
    const error = `Error talking to server: ${errorAction.message}`
    if(state.latestError) {
        const errorList = state.previousErrors.length ?
            [state.latestError] + state.previousErrors.slice(0, state.maxErrors - 2) :
            [state.latestError];
        return {
            ...state,
            latestError: error,
            previousErrors: errorList
        }
    }
    return {
        ...state,
        latestError: error
    }
};

const toggleDrawer = (state, action) => ({
   ...state,
    drawerOpen: !state.drawerOpen
});


const handlerMap = {
    [ERROR_COMMUNICATING_WITH_SERVER]: handleCommsError,
    [TOGGLE_DRAWER]: toggleDrawer
};

export const systemReducer = (state = initState, action) => {
    if (action) {
        const handler = handlerMap[action.type];
        if (handler) {
            return handler(state, action);
        }
    }
    return state;
};

export default systemReducer;