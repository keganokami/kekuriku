const middleware = {}

middleware['auth-gard'] = require('../middleware/auth-gard.js')
middleware['auth-gard'] = middleware['auth-gard'].default || middleware['auth-gard']

export default middleware
