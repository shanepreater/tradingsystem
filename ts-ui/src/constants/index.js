export const API_BASE_URL = 'http://localhost:8080';
export const CLIENT_BASE_URL = 'http://localhost:3000';

// Add some local storage hints.
export const ACCESS_TOKEN = 'accessToken';
export const DISCLAIMER_VERSION_ACCEPTED = 'disclaimerVersion'

export const OAUTH2_REDIRECT_URI = `${CLIENT_BASE_URL}/oauth2/redirect`;

export const GOOGLE_AUTH_URL = `${API_BASE_URL}/oauth2/authorize/google?redirect_uri=${OAUTH2_REDIRECT_URI}`;
export const FACEBOOK_AUTH_URL = `${API_BASE_URL}/oauth2/authorize/facebook?redirect_uri=${OAUTH2_REDIRECT_URI}`;
export const GITHUB_AUTH_URL = `${API_BASE_URL}/oauth2/authorize/github?redirect_uri=${OAUTH2_REDIRECT_URI}`;
