export const helpers = {
  getAllFromTable : (strTableName, res, pool) => {
    let queryString = `SELECT * FROM ${strTableName}`;
    pool.query(queryString)
    .then(result => res.json(result.rows))
  },
  validateBlank : (requiredFields, formData) => {
    let errors = {};
    for (let field of Object.keys(requiredFields)) {
      if (formData[field].trim() === "") errors[field] = requiredFields[field];
    }
    return errors;
  }
}