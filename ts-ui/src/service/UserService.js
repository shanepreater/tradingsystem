import axios from 'axios'
import {buildAxiosConfig} from "./Helper";
import {ACCESS_TOKEN, API_BASE_URL} from "../constants";

export const fireAcceptDisclaimer = (user, disclaimerAccepted) => {
    let config = buildAxiosConfig();
    return axios.post(`${API_BASE_URL}/user/me/acceptDisclaimer`, disclaimerAccepted, config)
};

export const getCurrentUser = () => {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    let config = buildAxiosConfig();
    return axios.get(`${API_BASE_URL}/user/me`, config)
};

export const loginRequest = (loginRequest) => {
    const config = buildAxiosConfig();
    return axios.post(`${API_BASE_URL}/auth/login`, JSON.stringify(loginRequest), config);
};

export const signupRequest = (signupRequest) => {
    const config = buildAxiosConfig();
    return axios.post(`${API_BASE_URL}/auth/signup`, JSON.stringify(signupRequest), config);
};
