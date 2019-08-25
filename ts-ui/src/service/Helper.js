import {ACCESS_TOKEN} from "../constants";

export const buildAxiosConfig = (user = null) => {
    let token = localStorage.getItem(ACCESS_TOKEN);
    return token ? {
        headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json"
        }
    } : {}
};