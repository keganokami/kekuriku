export default function ({$cookies, store, redirect, route, $auth }) {

    const token = $cookies.get('XSRF-TOKEN');

    if (route.name === 'login' && !store.getters.isAuthenticated) {
        return false
    }
    if (!store.getters.isAuthenticated && !token) {
        return redirect('/login')
    }
}