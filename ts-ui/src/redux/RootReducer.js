import authReducer from './AuthReducer'
import configReducer from './ConfigReducer'
import systemReducer from "./SystemReducer";
import {combineReducers} from "redux";

const rootReducer = combineReducers({
    auth: authReducer,
    config: configReducer,
    system: systemReducer
})
export default rootReducer