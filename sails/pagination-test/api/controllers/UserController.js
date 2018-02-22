/**
 * UserController
 *
 * @description :: Server-side logic for managing users
 * @help        :: See http://sailsjs.org/#!/documentation/concepts/Controllers
 */

module.exports = {


  findUsers: function(req, res) {
    var users = User.find().paginate({page:req.query.page, limit: 2});
    res.send(users);
  }
	
};

