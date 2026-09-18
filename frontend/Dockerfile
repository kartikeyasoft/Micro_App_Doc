FROM nginx:alpine
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/conf.d/nginx.conf
COPY fdata /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]