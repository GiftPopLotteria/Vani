                try {
                    List<Toy> list = tf.select();
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                } catch (SQLException ex) {
                    //Hien trang thong bao loi
                    ex.printStackTrace();//in thong bao loi chi tiet cho developer
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                }
                break;
            case "create": //hien form de nhap dulieu
                request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                break;

            case "create_handler": {//xu ly create form
                String op = request.getParameter("op");
                switch (op) {
                    case "create":
                        try {
                            //Đọc dữ liệu từ client gửi lên
                            String id = request.getParameter("id");
                            String name = request.getParameter("name");
                            double price = Double.parseDouble(request.getParameter("price"));
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date expDate = sdf.parse(request.getParameter("expDate"));
                            String brandId = request.getParameter("brandId");
                            //Tạo đối tượng toy
                            Toy toy = new Toy(id, name, price, expDate, brandId);
                            //Luu toy vao request de bao ton trang thai toy
                            request.setAttribute("toy", toy);
                            tf.create(toy);
                            //Hiển thị danh sách các mẫu tin của table toy
                            /*
                            List<Toy> list = tf.select();
                            request.setAttribute("list", list);
                            request.getRequestDispatcher("/toy.jsp").forward(request, response);
                             */
                            response.sendRedirect(request.getContextPath() + "/toy/index.do");
                        } catch (Exception ex) {
                            //Hien lai form để nhập lại dữ liệu
                            ex.printStackTrace();//in thong bao loi chi tiet cho developer
                            request.setAttribute("action", "create");
                            request.setAttribute("message", ex.getMessage());
                            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                        }
                        break;

                    case "cancel":
                        response.sendRedirect(request.getContextPath() + "/toy/index.do");
                        break;
                }
            }
            break;
            case "edit"://Hien form de sua du lieu
                try {
                    //Đọc mẫu tin cần sửa vào đối tượng toy
                    String id = request.getParameter("id");
                    Toy toy = tf.read(id);
                    //Lưu toy vào request để truyền cho view edit.jsp
                    request.setAttribute("toy", toy);
                    //Chuyển request & response đến view edit.jsp để xử lý tiếp
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                } catch (SQLException ex) {
                    //Hien trang thong bao loi
                    ex.printStackTrace();//in thong bao loi chi tiet cho developer
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);

                }
                break;
            case "edit_handler": {//Luu thong tin vao db
                String op = request.getParameter("op");
                switch (op) {
                    case "update":
                        try {
                            //Đọc dữ liệu từ client gửi lên
                            String id = request.getParameter("id");
                            String name = request.getParameter("name");
                            double price = Double.parseDouble(request.getParameter("price"));
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date expDate = sdf.parse(request.getParameter("expDate"));
                            String brandId = request.getParameter("brandId");
                            //Cập nhật dữ liệu vào db
                            Toy toy = new Toy(id, name, price, expDate, brandId);
                            tf.update(toy);
                            //Hiển thị danh sách các mẫu tin của table toy
                            /*
                            List<Toy> list = tf.select();
                            request.setAttribute("list", list);
                            request.getRequestDispatcher("/toy.jsp").forward(request, response);
                             */
                            response.sendRedirect(request.getContextPath() + "/toy/index.do");
                        } catch (Exception ex) {
                            //Hien trang thong bao loi
                            ex.printStackTrace();//in thong bao loi chi tiet cho developer
                            request.setAttribute("message", ex.getMessage());
                            request.getRequestDispatcher("/error.jsp").forward(request, response);
                        }
                        break;

                    case "cancel":
                        response.sendRedirect(request.getContextPath() + "/toy/index.do");
                        break;
                }
            }
            break;
            case "delete":
                try {
                    String id = request.getParameter("id");
                    tf.delete(id);
                    //Chuyen den trang /toy?op=list
                    response.sendRedirect(request.getContextPath() + "/toy?op=list");
                } catch (SQLException ex) {
                    //Hien trang thong bao loi
                    ex.printStackTrace();//in thong bao loi chi tiet cho developer
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
                break;