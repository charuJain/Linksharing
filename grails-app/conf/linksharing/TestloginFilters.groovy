package linksharing

class TestloginFilters {

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                if (!session.currentUser && controllerName && !(controllerName in ['user'] && actionName in ['loginHandler', 'registerHandler', 'save'])) {
                    redirect(controller: 'user', action: 'loginHandler')
                    return false
                }
            }

            after = {

            }

            afterView = {

            }
        }
    }

}
