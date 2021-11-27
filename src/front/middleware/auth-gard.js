export default function ({store, redirect, route }) {
    const token = localStorage.getItem('token');

    if (route.name === 'login' && !store.getters.isAuthenticated) {
        return false
    }
    if (!store.getters.isAuthenticated && !token) {
        return redirect('/login')
    }
}